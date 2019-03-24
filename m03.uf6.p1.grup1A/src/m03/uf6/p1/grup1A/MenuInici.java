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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Cho_S
 */
public class MenuInici extends JFrame {

    MenuInici() {

        creaComponents();
        accionListener();
    }

    private JPanel jPrincipal;
    private JPanel jPanel;
    private JPanel jOpciones;
    private JLabel jTitol;
    private JButton jBtnPacients;
    private JButton jBtnMetges;
    private JButton jBtnMalalties;
    private JButton jBtnVisitas;

    private ImageIcon imageIcon;
    private Image newimg;
    private JLabel img;

    private void creaComponents() {
        setTitle("Gestió Hospital");
        setDefaultLookAndFeelDecorated(true);
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

        jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(numBorder, numBorder, numBorder, numBorder));
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));

        numBorder = 10;
        jTitol = new JLabel("Gestió Hospital");
        jTitol.setBorder(new EmptyBorder(numBorder, numBorder, numBorder, numBorder));
        jTitol.setFont(new Font("Serif", Font.PLAIN, 20));

        jPanel.add(jTitol);

        jOpciones = new JPanel(new FlowLayout());

        imageIcon = new ImageIcon(this.getClass().getResource("/Imagenes/patientIcon.jpg"));
        newimg = imageIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        img = new JLabel(imageIcon);

        numBorder = 10;
        EmptyBorder borderBtn = new EmptyBorder(numBorder, numBorder, numBorder, numBorder);
        jBtnPacients = new JButton("Gestió Pacients");
        jBtnPacients.setBorder(borderBtn);

        jOpciones.add(img);
        jOpciones.add(jBtnPacients);
        jPanel.add(jOpciones);

        jBtnMetges = new JButton("Gestió Metges");
        jBtnMetges.setBorder(borderBtn);

        imageIcon = new ImageIcon(this.getClass().getResource("/Imagenes/metgeIcon.jpg"));
        newimg = imageIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        img = new JLabel(imageIcon);
        jOpciones = new JPanel(new FlowLayout());
        jOpciones.add(img);
        jOpciones.add(jBtnMetges);
        jPanel.add(jOpciones);

        jBtnMalalties = new JButton("Gestió Malalties");
        jBtnMalalties.setBorder(borderBtn);

        imageIcon = new ImageIcon(this.getClass().getResource("/Imagenes/enfermerdadIcon.jpg"));
        newimg = imageIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        img = new JLabel(imageIcon);
        jOpciones = new JPanel(new FlowLayout());
        jOpciones.add(img);
        jOpciones.add(jBtnMalalties);
        jPanel.add(jOpciones);

        jBtnVisitas = new JButton("Gestió Visitas");
        jBtnVisitas.setBorder(borderBtn);

        imageIcon = new ImageIcon(this.getClass().getResource("/Imagenes/visitaIcon.png"));
        newimg = imageIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        img = new JLabel(imageIcon);
        jOpciones = new JPanel(new FlowLayout());
        jOpciones.add(img);
        jOpciones.add(jBtnVisitas);
        jPanel.add(jOpciones);

        jPrincipal.add(jPanel, BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void accionListener() {
        jBtnPacients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaInfo frame = new TablaInfo(1);
                frame.setVisible(true);
            }
        });
        jBtnMetges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaInfo frame = new TablaInfo(0);
                frame.setVisible(true);
            }
        });
        jBtnMalalties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaInfo frame = new TablaInfo(3);
                frame.setVisible(true);
            }
        });
        jBtnVisitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaInfo frame = new TablaInfo(2);
                frame.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuInici frame = new MenuInici();
                frame.setVisible(true);
            }
        });
    }
}
