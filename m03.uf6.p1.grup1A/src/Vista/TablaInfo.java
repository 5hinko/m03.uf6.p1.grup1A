/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.*;
import Modelo.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Cho_S
 */
public class TablaInfo extends JFrame {

    public static int itemCheck;
    private static final EnumTablas listObject[] = EnumTablas.values();

    public TablaInfo(int num) {
        itemCheck = num;

        creaComponents();
        accionListener();

    }

    public JPanel jPrincipal;
    public JPanel jPanel;
    public JPanel jPanelConf;

    public static JTextField jTxtFieldBusca;
    public static JLabel jTitol;
    public static JButton jBtnInsert;
    public static JButton jBtnBusca;
    public static JComboBox jCombo;
    public static JTable jTablaInfo;
    public static JScrollPane jScroll;

    private void creaComponents() {
        setDefaultLookAndFeelDecorated(true);
        setTitle("Gestió Hospital");
        setLayout(new FlowLayout());

        try {
            //UIManager.setLookAndFeel("javax.swing.plat.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int numBorder;
        numBorder = 20;

        jPrincipal = new JPanel(new BorderLayout());
        jPrincipal.setBorder(new EmptyBorder(numBorder, numBorder, numBorder, numBorder));
        add(jPrincipal);

        //1r Row
        jTitol = new JLabel("Titol");
        jCombo = new JComboBox();

        jPanel = new JPanel(new GridLayout(1, 0));

        jTitol.setHorizontalAlignment(JLabel.CENTER);
        jTitol.setFont(new Font("Serif", Font.PLAIN, 20));

        jCombo.addItem("Item 1");
        jCombo.addItem("Item 2");

        jPanel.add(new JLabel(""));
        jPanel.add(jTitol);
        jPanel.add(jCombo);

        jPrincipal.add(jPanel, BorderLayout.NORTH);

        //2n Row
        jTablaInfo = new JTable();
        jScroll = new JScrollPane(jTablaInfo,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jScroll.setPreferredSize(new Dimension(720, 300));

        jTablaInfo.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        //jScroll.setViewportView(jTablaInfo);

        jPanel = new JPanel(new FlowLayout());
        jPanel.add(jScroll);
        jPrincipal.add(jPanel, BorderLayout.CENTER);

        //3r Row
        jTxtFieldBusca = new JTextField();
        jBtnBusca = new JButton("Search");
        jBtnInsert = new JButton("Insert");

        jTxtFieldBusca.setText("");
        jTxtFieldBusca.setColumns(20);

        jPanelConf = new JPanel(new FlowLayout());

        jPanelConf.add(jTxtFieldBusca);
        jPanelConf.add(jBtnBusca);

        jPanel = new JPanel(new BorderLayout());
        jPanel.add(jPanelConf, BorderLayout.WEST);
        jPanel.add(jBtnInsert, BorderLayout.EAST);

        jPrincipal.add(jPanel, BorderLayout.SOUTH);

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private void accionListener() {

        cambiosEnComboBox();

        //
        jTablaInfo.getModel().addTableModelListener(new TablaInfoAction());

        //Cambio en la lista
        jCombo.addActionListener(new ComboBoxAction());

        //Busca
        jBtnBusca.addActionListener(new BotonTablaInfoBuscar());

        //Insert
        jBtnInsert.addActionListener(new BotonInsertDatos());
        //Field
        /*jTxtFieldBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jBtnBusca.doClick();
            }
        });*/
        jTxtFieldBusca.addKeyListener(new FieldBuscar());
    }

    public static void actualitzaTaula(JTable taula, TableModel model) {
        taula.setModel(model);
        /*
        RenderizadorCeldas renderizador = new RenderizadorCeldas();
        for (int i = 0; i < taula.getColumnCount(); i++) {
            taula.getColumnModel().getColumn(i).setCellRenderer(renderizador);
        }*/
        // Nom mostrarem la columna id a la taula, però la necessitem per fer el update
        // si editem un camp
        /*TableColumnModel tcm = taula.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));*/
        if (EnumTablas.getEnum(itemCheck) == EnumTablas.Malalties) {
            TableColumnModel tcm = taula.getColumnModel();
            tcm.removeColumn(tcm.getColumn(0));
        }
    }

    public static void cambiosEnComboBox() {
        jCombo.removeAllItems();

        jCombo.addItem(EnumTablas.getEnum(itemCheck));
        for (int i = 0; i < listObject.length; i++) {
            if (i == itemCheck) {
                jTitol.setText("Gestió " + listObject[i]);
                //jCombo.addItem(listObject[i]);
            } else {
                jCombo.addItem(listObject[i]);
            }
        }
        jCombo.setSelectedItem(EnumTablas.getEnum(itemCheck));

        selectAllInTablaRefresh();
        jTxtFieldBusca.setText("");
    }

    public static void selectAllInTablaRefresh() {
        try {

            ResultSet resultat = null;
            PreparedStatement statement = null;
            Connection con = null;

            con = ConnexionUser.getConnection();
            //Reset Select ALL
            String sQuery = "SELECT * "
                    + " FROM " + EnumTablas.getEnum(itemCheck);
            statement = con.prepareStatement(sQuery);

            statement.executeQuery();
            
            resultat = statement.getResultSet();

            TableModel model = new ModeloDeTablaSimple(resultat);
            actualitzaTaula(jTablaInfo, model);
        } catch (SQLException ex) {
            Logger.getLogger(TablaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TablaInfo frame = new TablaInfo(1);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
*/
}
