package Vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class FormularioInsert extends JFrame {

    public JFrame JFwindow;
    public GridBagConstraints c;
    public JPanel jPrincipal;
    public JPanel JPcontent;
    public JButton JBtnCrea;
    public JButton jBtnCancelar;

    public JTextField JTxtFldNom;
    public JTextField JTxtFldPrimerCognom;
    public JTextField JTxtFldSegonCognom;
    public JTextField JTxtFldNumeroSS;
    public JTextField JTxtFldNIF;
    public JTextField JTxtFldTelf;
    public JTextField JTxtFldCodiPostal;
    public JTextField JTxtFldCiutat;
    public JTextField JTxtFldCarrer;
    public JTextField JTxtFldNumero;
    public JTextField JTxtFldPlanta;
    public JTextField JTxtFldPorta;

    public JTextField JTxtFldSalariMensual;
    public JTextField JTxtFldNumEmpleat;
    public JTextField JTxtFldCompteCorrent;

    public String[] data;
    public int modus;

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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
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

        JPanel compteCorrent = new JPanel();
        JPcontent.add(compteCorrent);
        compteCorrent.add(new JLabel("Compte corrent:  "));
        JTxtFldCompteCorrent = new JTextField(22);
        JTxtFldCompteCorrent.addComponentListener(null);
        compteCorrent.add(JTxtFldCompteCorrent);
        
        JPanel salariMensual = new JPanel();
        JPcontent.add(salariMensual);
        salariMensual.add(new JLabel("Salari menusal:    "));
        JTxtFldSalariMensual = new JTextField(22);
        JTxtFldSalariMensual.addComponentListener(null);
        salariMensual.add(JTxtFldSalariMensual);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            FormularioInsert frame = new FormularioInsert();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

    }
}