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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Cho_S
 */
public class CreaNuevo extends JFrame {

    private final int itemCheck;
    private final String[] datosAIntroducir;

    CreaNuevo() {
        itemCheck = 0;
        //itemCheck = TablaInfo.itemCheck;

        //Connectar con DB para sacar nombres
        datosAIntroducir = new String[]{"Cheese", "Pepperoni", "Black Olives"};

        creaComponents();
        accionListener();

    }

    private JPanel jPrincipal;
    private JPanel jPanel;
    private JPanel jPanelConf;

    private JTextField jTxtList[];
    private JLabel jTitol;
    private JButton jBtnCrea;
    private JButton jBtnCancelar;
    private JScrollPane jScroll;

    private void creaComponents() {
        setDefaultLookAndFeelDecorated(true);
        setTitle("Crear " + EnumTablas.getEnum(itemCheck));
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

        //NORTH
        jTitol = new JLabel("Crear " + EnumTablas.getEnum(itemCheck));

        jPanel = new JPanel(new GridLayout(1, 0));

        jTitol.setHorizontalAlignment(JLabel.CENTER);
        jTitol.setFont(new Font("Serif", Font.PLAIN, 20));

        jPanel.add(jTitol);

        jPrincipal.add(jPanel, BorderLayout.NORTH);

        //CENTER
        jScroll = new JScrollPane();
        jPanel = new JPanel();
                jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
        jTxtList = new JTextField[datosAIntroducir.length];

        for (int i = 0; i < datosAIntroducir.length; i++) {
            jPanelConf = new JPanel(new FlowLayout());
            jTitol = new JLabel(datosAIntroducir[i] + ": ");

            jTxtList[i] = new JTextField("");
            jTxtList[i].setColumns(20);

            jPanelConf.add(jTitol);
            jPanelConf.add(jTxtList[i]);

            jPanel.add(jPanelConf);
        }

        jScroll.add(jPanel);
        jPrincipal.add(jScroll, BorderLayout.CENTER);

        //SOUTH
        jBtnCancelar = new JButton("Cancelar");
        jBtnCrea = new JButton("Crear");

        jPanel = new JPanel(new BorderLayout());
        jPanel.add(jBtnCancelar, BorderLayout.WEST);
        jPanel.add(jBtnCrea, BorderLayout.EAST);

        jPrincipal.add(jPanel, BorderLayout.SOUTH);

        pack();
    }

    private void accionListener() {

        //Busca
        jBtnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        //Insert
        jBtnCrea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Nuevo para crear

                for (JTextField texto : jTxtList) {
                    texto.getText().toString();
                }
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreaNuevo frame = new CreaNuevo();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

}