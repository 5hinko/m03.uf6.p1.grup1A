/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf6.p1.grup1A;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Cho_S
 */
public class TablaInfo extends JFrame {

    private int itemCheck;

    TablaInfo(int num) {
        itemCheck = num;

        creaComponents();
        accionListener();

        TableModel modelBuit = new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        );
        jTablaInfo.setModel(modelBuit);
    }

    private JPanel jPrincipal;
    private JPanel jPanel;
    private JPanel jPanelConf;
    private JTextField jTxtField;
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
        jTxtField = new JTextField();
        jBtnBusca = new JButton("Search");
        jBtnInsert = new JButton("Insert");

        jTxtField.setText("");
        jTxtField.setColumns(20);

        jPanelConf = new JPanel(new FlowLayout());

        jPanelConf.add(jTxtField);
        jPanelConf.add(jBtnBusca);

        jPanel = new JPanel(new BorderLayout());
        jPanel.add(jPanelConf, BorderLayout.WEST);
        jPanel.add(jBtnInsert, BorderLayout.EAST);

        jPrincipal.add(jPanel, BorderLayout.SOUTH);

        pack();
    }

    private void accionListener() {

        insertItemToComboBox();

        jCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Comienza en 1
                itemCheck = jCombo.getSelectedIndex() - 1;
                insertItemToComboBox();
            }
        });
    }

    private void insertItemToComboBox() {
        jCombo.removeAllItems();
        String listObject[] = {"Metge", "Pacient", "Visita", "Malaltia"};

        jCombo.addItem(listObject[itemCheck]);
        for (int i = 0; i < listObject.length; i++) {
            if (i == itemCheck) {
                jTitol.setText("Gestió " + listObject[i]);
                //jCombo.addItem(listObject[i]);
            } else {
                jCombo.addItem(listObject[i]);
            }
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
