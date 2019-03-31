package Vista;

import Controlador.ComboBoxVisitaNova;
import Controlador.BotonVisitaNovaCrea;
import Modelo.Connexion;
import Modelo.EnumTablas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class VisitaNova extends JFrame {

    public static GridBagConstraints c;
    public static JPanel JPtitle;
    public static JPanel JPcontent;
    public static JButton JBtnCrea;

    public static JComboBox jComboCercaPacient;
    public static JComboBox jComboCercaMetge;
    public static JComboBox jComboCercaMalaltia;
    public static JLabel jLblInformacionPacient;
    public static JLabel jLblInformacionMetge;
    public static JLabel jLblInformacionMalaltia;

    public static JPanel JPdata;
    public static String[] months = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Septembre", "Octubre", "Novembre", "Desembre"};
    public static JComboBox JCBoxMonths;
    public static JLabel JLblDays;
    public static JLabel JLblYears;
    public static JTextField JTxtFldYear;
    public static JTextField JTxtFldDays;
    public static JTextField JTxtFldHour;
    public static JTextField JTxtFldMin;

    public static JPanel JPmalaltia;
    public static JLabel JLblNomMalaltia;
    public static JPanel JPpacient;
    public static JLabel JLblNomPacient;
    public static JPanel JPmetge;
    public static JLabel JLblNomMetge;

    public VisitaNova() {

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

        JPtitle.add(new JLabel("NOVA VISITA"));

        // Content.
        JPcontent = new JPanel();
        JPcontent.setLayout(new BoxLayout(JPcontent, BoxLayout.PAGE_AXIS));
        c.gridy = 1;
        add(JPcontent, c);

        JPmalaltia = new JPanel();
        JPcontent.add(JPmalaltia);
        JLblNomMalaltia = new JLabel("Introdueix una malaltia.");
        jComboCercaMalaltia = new JComboBox();
        jComboCercaMalaltia.addItem("              ");
        jLblInformacionMalaltia = new JLabel("");

        JPmalaltia.add(JLblNomMalaltia);
        JPmalaltia.add(jComboCercaMalaltia);
        JPmalaltia.add(new JLabel("Resultado:  "));
        JPmalaltia.add(jLblInformacionMalaltia);

        JPpacient = new JPanel();
        JPcontent.add(JPpacient);
        JLblNomPacient = new JLabel("Introdueix un pacient.   ");
        jComboCercaPacient = new JComboBox();
        jComboCercaPacient.addItem("              ");
        jLblInformacionPacient = new JLabel("");

        JPpacient.add(JLblNomPacient);
        JPpacient.add(jComboCercaPacient);
        JPpacient.add(new JLabel("Resultado:  "));
        JPpacient.add(jLblInformacionPacient);

        JPmetge = new JPanel();
        JPcontent.add(JPmetge);
        JLblNomMetge = new JLabel("Introdueix un metge.    ");
        jComboCercaMetge = new JComboBox();
        jComboCercaMetge.addItem("              ");
        jLblInformacionMetge = new JLabel("");

        JPmetge.add(JLblNomMetge);
        JPmetge.add(jComboCercaMetge);
        JPmetge.add(new JLabel("Resultado:  "));
        JPmetge.add(jLblInformacionMetge);

        //Select Data.
        JPdata = new JPanel();
        JPcontent.add(JPdata);

        JCBoxMonths = new JComboBox(months);
        JCBoxMonths.setSelectedIndex(0);

        JPdata.add(JCBoxMonths);

        JPdata.add(new JLabel("   "));

        JLblDays = new JLabel("Dia [1 a 31]:");
        JPdata.add(JLblDays);

        JTxtFldDays = new JTextField(2);
        JPdata.add(JTxtFldDays);

        JLblYears = new JLabel("Any");
        JPdata.add(JLblYears);

        JTxtFldYear = new JTextField(4);
        JTxtFldYear.addComponentListener(null);
        JPdata.add(JTxtFldYear);

        JPdata.add(new JLabel("Hora"));

        JTxtFldHour = new JTextField(2);
        JTxtFldHour.addComponentListener(null);
        JPdata.add(JTxtFldHour);

        JPdata.add(new JLabel(":"));

        JTxtFldMin = new JTextField(2);
        JTxtFldMin.addComponentListener(null);
        JPdata.add(JTxtFldMin);

        JPdata.add(new JLabel("Minut"));

        // Button.
        JPanel boto = new JPanel();
        boto.setBorder(new EmptyBorder(0, 0, 8, 0));
        JPcontent.add(boto);

        JBtnCrea = new JButton("Crear");
        boto.add(JBtnCrea);

        //pack();
        setSize(700, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private void ActionToListener() {

        try {
            con = Connexion.getConnectionAdmin();
            RellenarComboMalaltia();
            RellenarComboMetge();
            RellenarComboPacient();
        } catch (SQLException ex) {
            Logger.getLogger(VisitaNova.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(VisitaNova.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        jComboCercaMalaltia.addActionListener(new ComboBoxVisitaNova());
        jComboCercaMetge.addActionListener(new ComboBoxVisitaNova());
        jComboCercaPacient.addActionListener(new ComboBoxVisitaNova());
        JCBoxMonths.addActionListener(new ComboBoxVisitaNova());
        JBtnCrea.addActionListener(new BotonVisitaNovaCrea());
    }

    private Connection con = null;
    private ResultSet resultat = null;
    private PreparedStatement statement = null;
    private String sQuery;

    private void RellenarComboMalaltia() {
        try {
            con = Connexion.getConnection();

            sQuery = ("SELECT * FROM " + EnumTablas.Malalties);

            statement = con.prepareStatement(sQuery);

            statement.executeQuery();
            resultat = statement.getResultSet();

            jComboCercaMalaltia.removeAllItems();
            while (resultat.next()) {
                jComboCercaMalaltia.addItem(resultat.getString("codi"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(VisitaNova.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(VisitaNova.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void RellenarComboMetge() {
        try {
            con = Connexion.getConnection();

            sQuery = ("SELECT * FROM " + EnumTablas.Metges);

            statement = con.prepareStatement(sQuery);

            statement.executeQuery();
            resultat = statement.getResultSet();

            jComboCercaMetge.removeAllItems();
            while (resultat.next()) {
                jComboCercaMetge.addItem(resultat.getString("DNI"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(VisitaNova.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(VisitaNova.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void RellenarComboPacient() {
        try {
            con = Connexion.getConnection();

            sQuery = ("SELECT * FROM " + EnumTablas.Pacients);

            statement = con.prepareStatement(sQuery);

            statement.executeQuery();
            resultat = statement.getResultSet();

            jComboCercaPacient.removeAllItems();
            while (resultat.next()) {
                jComboCercaPacient.addItem(resultat.getString("DNI"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(VisitaNova.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(VisitaNova.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
/*
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            VisitaNova frame = new VisitaNova();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
*/
}
