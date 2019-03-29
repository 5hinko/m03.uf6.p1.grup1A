package Vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class NovaMalaltia extends JFrame {

    GridBagConstraints c;
    public static JPanel JPtitle;
    public static JPanel JPcontent;
    public static JButton JBtnCrea;

    public static JTextField JTxtFldNom;
    public static JCheckBox JCkBoxCausaBaixa;
    public static boolean causaBaixa = false;
    public static JTextArea JTxtAreaTractament;
    public static JTextField JTxtFldDuradaTractament;

    public NovaMalaltia() {

        CreaComponents();
        ActionToListener();
    }

    private void CreaComponents() {

        setDefaultLookAndFeelDecorated(true);
        try {
            //UIManager.setLookAndFeel("javax.swing.plat.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Window.
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 0;

        // Title
        JPtitle = new JPanel();
        JPtitle.setLayout(new BoxLayout(JPtitle, BoxLayout.PAGE_AXIS));
        JPtitle.setBackground(Color.LIGHT_GRAY);
        JPtitle.setBorder(new EmptyBorder(5, 5, 5, 5));
        c.gridy = 0;
       add(JPtitle, c);

        JPtitle.add(new JLabel("NOVA MALALTIA"));

        // Content.
        JPcontent = new JPanel();
        JPcontent.setLayout(new BoxLayout(JPcontent, BoxLayout.PAGE_AXIS));
        c.gridy = 1;
        add(JPcontent, c);

        JPanel nom = new JPanel();
        JPcontent.add(nom);
        nom.add(new JLabel("Nom: "));
        JTxtFldNom = new JTextField(28);
        JTxtFldNom.addComponentListener(null);
        nom.add(JTxtFldNom);

        JPanel duradaTractamentCausaBaixa = new JPanel();
        JPcontent.add(duradaTractamentCausaBaixa);
        duradaTractamentCausaBaixa.add(new JLabel("Causa Baixa:"));
        JCkBoxCausaBaixa = new JCheckBox(); //ActionListener downwards.
        duradaTractamentCausaBaixa.add(JCkBoxCausaBaixa);
        duradaTractamentCausaBaixa.add(new JLabel("  Durada Tractament:  "));
        JTxtFldDuradaTractament = new JTextField(10);
        JTxtFldDuradaTractament.addComponentListener(null);
        duradaTractamentCausaBaixa.add(JTxtFldDuradaTractament);

        JPanel tractament = new JPanel();
        JPcontent.add(tractament);
        tractament.add(new JLabel("Tractament :  "));
        JTxtAreaTractament = new JTextArea(10, 24);
        JTxtAreaTractament.addComponentListener(null);
        JTxtAreaTractament.setLineWrap(true);
        JTxtAreaTractament.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(JTxtAreaTractament);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tractament.add(areaScrollPane);

        //CheckBox

        // Button.
        JPanel boto = new JPanel();
        boto.setBorder(new EmptyBorder(0, 0, 8, 0));
        JPcontent.add(boto);

        JBtnCrea = new JButton("Crear");
        boto.add(JBtnCrea);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void ActionToListener() {/*
        JCkBoxCausaBaixa.addActionListener(new ButonsNovaMalaltia());
        JBtnCrea.addActionListener(new ButonsNovaMalaltia());*/  
    }
        public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                NovaMalaltia frame = new NovaMalaltia();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
