/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Connexion;
import Modelo.EnumTablas;
import Vista.VisitaNova;
import static Vista.VisitaNova.JCBoxMonths;
import static Vista.VisitaNova.JLblDays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cho_S
 */
public class ComboBoxVisitaNova implements ActionListener {

    private Connection con = null;
    private ResultSet resultat = null;
    private PreparedStatement statement = null;
    private String sQuery;
    private String sParaBuscar;
    private ActionEvent e;

    @Override
    public void actionPerformed(ActionEvent e) {
        this.e = e;
        if (e.getSource() == JCBoxMonths) {
            String mes = (String) JCBoxMonths.getSelectedItem();
            switch (mes) {
                case "Gener":
                    JLblDays.setText("Dia [1,31]:");
                    break;
                case "Febrer":
                    JLblDays.setText("Dia [1,28]:");
                    break;
                case "Març":
                    JLblDays.setText("Dia [1,31]:");
                    break;
                case "Abril":
                    JLblDays.setText("Dia [1,30]:");
                    break;
                case "Maig":
                    JLblDays.setText("Dia [1,31]:");
                    break;
                case "Juny":
                    JLblDays.setText("Dia [1,30]:");
                    break;
                case "Juliol":
                    JLblDays.setText("Dia [1,31]:");
                    break;
                case "Agost":
                    JLblDays.setText("Dia [1,30]:");
                    break;
                case "Setembre":
                    JLblDays.setText("Dia [1,31]:");
                    break;
                case "Novembre":
                    JLblDays.setText("Dia [1,28]:");
                    break;
                case "Desembre":
                    JLblDays.setText("Dia [1,31]:");
                    break;
                default:
                    break;
            }
        } else {
            ComboBoxVisitaNovaCercaDades();
        }
    }

    private void ComboBoxVisitaNovaCercaDades() {
        try {
            con = Connexion.getConnection();
            if (e.getSource() == VisitaNova.jComboCercaMalaltia) {
                sParaBuscar = VisitaNova.jComboCercaMalaltia.getSelectedItem().toString();

                if (!sParaBuscar.equals("")) {
                    sQuery = ("SELECT * FROM " + EnumTablas.Malalties + " WHERE codi = ? ");

                    statement = con.prepareStatement(sQuery);

                    statement.setInt(1, Integer.parseInt(sParaBuscar));

                    statement.executeQuery();
                    resultat = statement.getResultSet();
                    
                    while (resultat.next()) {
                        VisitaNova.jLblInformacionMalaltia.setText(resultat.getString("nom"));
                    }
                }

            } else if (e.getSource() == VisitaNova.jComboCercaMetge) {
                sParaBuscar = VisitaNova.jComboCercaMetge.getSelectedItem().toString();

                if (!sParaBuscar.equals("")) {
                    sQuery = ("SELECT * FROM " + EnumTablas.Metges + " WHERE DNI LIKE ? ");

                    statement = con.prepareStatement(sQuery);

                    statement.setString(1, sParaBuscar);

                    statement.executeQuery();
                    resultat = statement.getResultSet();

                    while (resultat.next()) {
                        VisitaNova.jLblInformacionMetge.setText(resultat.getString("nom") + " " + resultat.getString("cognom1") + " " + resultat.getString("cognom2"));
                    }
                }

            } else if (e.getSource() == VisitaNova.jComboCercaPacient) {
                sParaBuscar = VisitaNova.jComboCercaPacient.getSelectedItem().toString();

                if (!sParaBuscar.equals("")) {
                    sQuery = ("SELECT * FROM " + EnumTablas.Pacients + " WHERE DNI LIKE ? ");

                    statement = con.prepareStatement(sQuery);

                    statement.setString(1, sParaBuscar);

                    statement.executeQuery();
                    resultat = statement.getResultSet();

                    while (resultat.next()) {
                        VisitaNova.jLblInformacionPacient.setText(resultat.getString("nom") + " " + resultat.getString("cognom1") + " " + resultat.getString("cognom2"));
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(BotonTablaInfoBuscar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComboBoxVisitaNova.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
