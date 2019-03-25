/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf6.p1.grup1A;

import Vista.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Cho_S
 */
public class TablaInfo extends JFrame {

    public static int itemCheck;
    private final EnumTablas listObject[] = EnumTablas.values();

    TablaInfo(int num) {
        itemCheck = num;

        creaComponents();
        accionListener();

    }

    private JPanel jPrincipal;
    private JPanel jPanel;
    private JPanel jPanelConf;
    private JTextField jTxtFieldBusca;
    private JLabel jTitol;
    private JButton jBtnInsert;
    private JButton jBtnBusca;
    private JComboBox jCombo;
    private JTable jTablaInfo;
    private JScrollPane jScroll;

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
    }

    private void accionListener() {

        cambiosEnComboBox();

        //
        jTablaInfo.getModel().addTableModelListener(new TableModelListener() {

            private int fila;
            private PreparedStatement sentencia = null;
            private Connection con = null;
            private TableModel dades;

            @Override
            public void tableChanged(TableModelEvent e) {
                fila = e.getFirstRow();
                dades = jTablaInfo.getModel();
                try {
                    con = Connexion.getConnection();
                    updateQuery();
                    sentencia.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(TablaInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void updateQuery() {
                try {
                    EnumTablas tablaEnum = EnumTablas.getEnum(itemCheck);
                    String consulta;
                    switch (tablaEnum) {
                        case Malalties:
                            consulta = "UPDATE " + tablaEnum + " SET nom = ?, causaBaixa = ?, tratamiento = ?, duradaTratamentDies = ? WHERE codi = ?";
                            sentencia = con.prepareStatement(consulta);
                            sentencia.setString(1, (String) (dades.getValueAt(fila, 1)));
                            sentencia.setInt(2, Integer.parseInt(dades.getValueAt(fila, 2).toString()));
                            sentencia.setString(3, (String) (dades.getValueAt(fila, 3)));
                            sentencia.setInt(4, Integer.parseInt(dades.getValueAt(fila, 4).toString()));
                            sentencia.setInt(5, Integer.parseInt(dades.getValueAt(fila, 0).toString()));
                            break;
                        case Visita:
                            consulta = "UPDATE " + tablaEnum + " SET dataVisita = ?, nomMalaltia = ?, dniPacient = ? , dniMetges = ? WHERE";
                            sentencia = con.prepareStatement(consulta);
                            sentencia.setString(1, (String) (dades.getValueAt(fila, 0)));
                            sentencia.setString(2, (String) (dades.getValueAt(fila, 1)));
                            sentencia.setString(3, (String) (dades.getValueAt(fila, 2)));
                            sentencia.setString(4, (String) (dades.getValueAt(fila, 3)));
                            break;
                        case Pacients:
                            consulta = "UPDATE " + tablaEnum + " SET nom = ?, cognom1 = ?, cognom2 = ?, numSS = ?, telefon = ? WHERE DNI = ?";
                            sentencia = con.prepareStatement(consulta);
                            sentencia.setString(1, (String) (dades.getValueAt(fila, 0)));
                            sentencia.setString(2, (String) (dades.getValueAt(fila, 1)));
                            sentencia.setString(3, (String) (dades.getValueAt(fila, 2)));
                            sentencia.setString(4, (String) (dades.getValueAt(fila, 4)));
                            sentencia.setString(5, (String) (dades.getValueAt(fila, 5)));
                            sentencia.setString(6, (String) (dades.getValueAt(fila, 3)));
                            break;
                        case Metges:
                            consulta = "UPDATE " + tablaEnum + " SET nom = ?, cognom1 = ?, cognom2 = ?, numSS = ?, telefon = ?, numEmpleat = ?, codiCC= ?, salariMensual = ? WHERE DNI = ?";
                            sentencia = con.prepareStatement(consulta);
                            sentencia.setString(1, (String) (dades.getValueAt(fila, 0)));
                            sentencia.setString(2, (String) (dades.getValueAt(fila, 1)));
                            sentencia.setString(3, (String) (dades.getValueAt(fila, 2)));
                            sentencia.setString(4, (String) (dades.getValueAt(fila, 4)));
                            sentencia.setString(5, (String) (dades.getValueAt(fila, 5)));
                            sentencia.setInt(6, Integer.parseInt(dades.getValueAt(fila, 6).toString()));
                            sentencia.setString(7, (String) (dades.getValueAt(fila, 7)));
                            sentencia.setInt(8, Integer.parseInt(dades.getValueAt(fila, 8).toString()));
                            sentencia.setString(9, (String) (dades.getValueAt(fila, 3)));

                            break;
                        default:
                            break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TablaInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Cambio en la lista
        jCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Comienza en 1
                itemCheck = EnumTablas.valueOf(jCombo.getSelectedItem().toString()).getNum();
                cambiosEnComboBox();
            }
        });

        //Busca
        jBtnBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sQuery = jTxtFieldBusca.getText().toString();
                if (sQuery.length() > 0) {
                    ResultSet resultat = selectQuery(sQuery);
                    TableModel model = new ModeloDeTablaSimple(resultat);
                    actualitzaTaula(jTablaInfo, model);
                } else {
                    cambiosEnComboBox();
                }
            }

            private ResultSet selectQuery(String sQuery) {
                ResultSet resultat;
                try {
                    EnumTablas tablaEnum = EnumTablas.getEnum(itemCheck);
                    switch (tablaEnum) {
                        case Malalties:
                            resultat = consultaBBDD(
                                    "SELECT * "
                                    + " FROM " + tablaEnum
                                    + " WHERE codi = " + sQuery + " OR "
                                    + "nom LIKE '" + sQuery + "' OR "
                                    + "tratamiento LIKE '" + sQuery + "' OR "
                                    + "duradaTratamentDies = " + sQuery
                            );
                            break;
                        case Visita:
                            resultat = consultaBBDD(
                                    "SELECT * "
                                    + " FROM " + tablaEnum
                                    + " WHERE dataVisita LIKE '" + sQuery + "' OR "
                                    + "nomMalaltia LIKE '" + sQuery + "' OR "
                                    + "dniPacient LIKE '" + sQuery + "' OR "
                                    + "dniMetges LIKE '" + sQuery + "'"
                            );
                            break;
                        default:
                            resultat = consultaBBDD(
                                    "SELECT * "
                                    + " FROM " + tablaEnum
                                    + " WHERE nom LIKE '" + sQuery + "' OR "
                                    + "cognom1 LIKE '" + sQuery + "' OR "
                                    + "cognom2 LIKE '" + sQuery + "' OR "
                                    + "cognomDNI LIKE '" + sQuery + "' OR "
                                    + "numSS LIKE '" + sQuery + "'"
                            );
                            break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TablaInfo.class.getName()).log(Level.SEVERE, null, ex);
                    resultat = null;
                }
                return resultat;
            }
        });

        //Insert
        jBtnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Nuevo para crear
                CreaNuevo frame = new CreaNuevo(itemCheck);
                frame.setVisible(true);
            }
        });
    }

    private ResultSet consultaBBDD(String consultaSQL) throws SQLException {

        Connection con = null;
        Statement sentencia = null;
        ResultSet resultat = null;
        try {
            con = Connexion.getConnection();
            sentencia = con.createStatement();
            sentencia.executeQuery(consultaSQL);
            resultat = sentencia.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    private void actualitzaTaula(JTable taula, TableModel model) {
        taula.setModel(model);
        /*
        RenderizadorCeldas renderizador = new RenderizadorCeldas();
        for (int i = 0; i < taula.getColumnCount(); i++) {
            taula.getColumnModel().getColumn(i).setCellRenderer(renderizador);
        }*/
        // Nom mostrarem la columna id a la taula, però la necessitem per fer el update
        // si editem un camp
        TableColumnModel tcm = taula.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
    }

    private void cambiosEnComboBox() {
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

        try {
            ResultSet resultat = consultaBBDD(
                    "SELECT * "
                    + " FROM " + EnumTablas.getEnum(itemCheck));
            TableModel model = new ModeloDeTablaSimple(resultat);

            actualitzaTaula(jTablaInfo, model);
        } catch (SQLException ex) {
            Logger.getLogger(TablaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TablaInfo frame = new TablaInfo(1);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

}
