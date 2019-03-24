package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FormularioInsert extends JFrame {

    public static JFrame JFwindow;
    public static GridBagConstraints c;
    public static JPanel jPrincipal;
    public static JPanel JPcontent;
    public static JButton JBtnCrea;
    public static JButton jBtnCancelar;

    public static JTextField JTxtFldNom;
    public static JTextField JTxtFldPrimerCognom;
    public static JTextField JTxtFldSegonCognom;
    public static JTextField JTxtFldNumeroSS;
    public static JTextField JTxtFldNIF;
    public static JTextField JTxtFldTelf;
    public static JTextField JTxtFldCodiPostal;
    public static JTextField JTxtFldCiutat;
    public static JTextField JTxtFldCarrer;
    public static JTextField JTxtFldNumero;
    public static JTextField JTxtFldPlanta;
    public static JTextField JTxtFldPorta;

    public static JTextField JTxtFldSalariMensual;
    public static JTextField JTxtFldNumEmpleat;
    public static JTextField JTxtFldCompteCorrent;

    public static String[] data;
    public static int modus;

    public FormularioInsert() {
        crearComponentes();
    }

    private void crearComponentes() {

        //Si funciona lo del item, poner un "+" en el titulo
        //also llamar al metodo metge();
        setTitle("Nueva Persona");
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

        JPcontent = new JPanel();
        JPcontent.setLayout(new BoxLayout(JPcontent, BoxLayout.PAGE_AXIS));
        numBorder = 10;

        JPanel nom = new JPanel();
        JPcontent.add(nom);
        nom.add(new JLabel("Nom: "));
        JTxtFldNom = new JTextField(28);
        JTxtFldNom.addComponentListener(null);
        nom.add(JTxtFldNom);

        JPanel primerCognom = new JPanel();
        JPcontent.add(primerCognom);
        primerCognom.add(new JLabel("Primer cognom:  "));
        JTxtFldPrimerCognom = new JTextField(22);
        JTxtFldPrimerCognom.addComponentListener(null);
        primerCognom.add(JTxtFldPrimerCognom);

        JPanel segonCognom = new JPanel();
        JPcontent.add(segonCognom);
        segonCognom.add(new JLabel("Segon cognom:   "));
        JTxtFldSegonCognom = new JTextField(22);
        JTxtFldSegonCognom.addComponentListener(null);
        segonCognom.add(JTxtFldSegonCognom);

        JPanel numeroSS = new JPanel();
        JPcontent.add(numeroSS);
        numeroSS.add(new JLabel("Número SS:  "));
        JTxtFldNumeroSS = new JTextField(24);
        JTxtFldNumeroSS.addComponentListener(null);
        numeroSS.add(JTxtFldNumeroSS);

        JPanel nif = new JPanel();
        JPcontent.add(nif);
        nif.add(new JLabel("NIF:   "));
        JTxtFldNIF = new JTextField(28);
        JTxtFldNIF.addComponentListener(null);
        nif.add(JTxtFldNIF);

        JPanel telf = new JPanel();
        JPcontent.add(telf);
        telf.add(new JLabel("Teléfon:  "));
        JTxtFldTelf = new JTextField(26);
        JTxtFldTelf.addComponentListener(null);
        telf.add(JTxtFldTelf);

        JPanel ciutat = new JPanel();
        JPcontent.add(ciutat);
        ciutat.add(new JLabel("Ciutat:  "));
        JTxtFldCiutat = new JTextField(27);
        JTxtFldCiutat.addComponentListener(null);
        ciutat.add(JTxtFldCiutat);

        JPanel codiPostal = new JPanel();
        JPcontent.add(codiPostal);
        codiPostal.add(new JLabel("Codi postal:   "));
        JTxtFldCodiPostal = new JTextField(24);
        JTxtFldCodiPostal.addComponentListener(null);
        codiPostal.add(JTxtFldCodiPostal);

        JPanel carrer = new JPanel();
        JPcontent.add(carrer);
        carrer.add(new JLabel("Carrer: "));
        JTxtFldCarrer = new JTextField(27);
        JTxtFldCarrer.addComponentListener(null);
        carrer.add(JTxtFldCarrer);

        JPanel numero = new JPanel();
        JPcontent.add(numero);
        numero.add(new JLabel("Número:  "));
        JTxtFldNumero = new JTextField(26);
        JTxtFldNumero.addComponentListener(null);
        numero.add(JTxtFldNumero);

        JPanel boto = new JPanel();
        boto.setBorder(new EmptyBorder(0, 0, 8, 0));
        JPcontent.add(boto);

        jBtnCancelar = new JButton("Cancelar");
        boto.add(jBtnCancelar);

        JBtnCrea = new JButton("Crear");
        boto.add(JBtnCrea);

        jPrincipal.add(JPcontent, BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void metge() {
        JPanel numEmpleat = new JPanel();
        JPcontent.add(numEmpleat);
        numEmpleat.add(new JLabel("Num empleat:   "));
        JTxtFldNumEmpleat = new JTextField(23);
        JTxtFldNumEmpleat.addComponentListener(null);
        numEmpleat.add(JTxtFldNumEmpleat);

        JPanel salariMensual = new JPanel();
        JPcontent.add(salariMensual);
        salariMensual.add(new JLabel("Salari menusal:    "));
        JTxtFldSalariMensual = new JTextField(22);
        JTxtFldSalariMensual.addComponentListener(null);
        salariMensual.add(JTxtFldSalariMensual);

        JPanel compteCorrent = new JPanel();
        JPcontent.add(compteCorrent);
        compteCorrent.add(new JLabel("Compte corrent:  "));
        JTxtFldCompteCorrent = new JTextField(22);
        JTxtFldCompteCorrent.addComponentListener(null);
        compteCorrent.add(JTxtFldCompteCorrent);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            FormularioInsert frame = new FormularioInsert();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

    }
}
