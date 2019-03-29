/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Connexion;
import Modelo.EnumTablas;
import Modelo.ModeloDeTablaSimple;
import Vista.TablaInfo;
import static Vista.TablaInfo.actualitzaTaula;
import static Vista.TablaInfo.itemCheck;
import static Vista.TablaInfo.jTablaInfo;
import static Vista.TablaInfo.jTxtFieldBusca;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author Cho_S
 */
public class BotonTablaInfoBuscar implements ActionListener {

    private Connection con = null;
    private ResultSet resultat = null;
    private PreparedStatement statement = null;
    private String sQuery;

    public BotonTablaInfoBuscar() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        sQuery = jTxtFieldBusca.getText().toString();
        if (sQuery.length() > 0) {

            try {
                con = Connexion.getConnection();
                resultat = selectQuery();
                statement.executeUpdate();
                TableModel model = new ModeloDeTablaSimple(resultat);
                actualitzaTaula(jTablaInfo, model);
            } catch (SQLException ex) {
                Logger.getLogger(BotonTablaInfoBuscar.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BotonTablaInfoBuscar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            TablaInfo.selectAllInTabla();
        }
    }

    private ResultSet selectQuery() throws SQLException {
        EnumTablas tablaEnum = EnumTablas.getEnum(itemCheck);
        switch (tablaEnum) {
            case Malalties:
                sQuery = ("SELECT * FROM " + tablaEnum + " WHERE codi = ? OR nom LIKE ? OR tratamiento LIKE ? OR duradaTratamentDies = ?");

                statement = con.prepareStatement(sQuery);

                try {
                    try {
                        statement.setInt(1, Integer.parseInt(sQuery));
                    } catch (Exception e) {

                    }
                    statement.setString(2, "%" + sQuery + "%");
                    statement.setString(3, "%" + sQuery + "%");
                    try {
                        statement.setInt(4, Integer.parseInt(sQuery));
                    } catch (Exception e) {

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BotonTablaInfoBuscar.class.getName()).log(Level.SEVERE, null, ex);
                }

            case Visita:
                sQuery = ("SELECT * FROM " + tablaEnum + " WHERE dataVisita LIKE ? OR "
                        + "nomMalaltia LIKE ? OR "
                        + "dniPacient LIKE ? OR "
                        + "dniMetges LIKE ?");

                statement = con.prepareStatement(sQuery);

                try {
                    statement.setString(1, "%" + sQuery + "%");
                    statement.setString(2, "%" + sQuery + "%");
                    statement.setString(3, "%" + sQuery + "%");
                    statement.setString(4, "%" + sQuery + "%");

                } catch (SQLException ex) {
                    Logger.getLogger(BotonTablaInfoBuscar.class.getName()).log(Level.SEVERE, null, ex);
                }

            default:
                sQuery = ("SELECT * "
                        + " FROM " + tablaEnum + " WHERE nom LIKE ? OR "
                        + "cognom1 LIKE ? OR "
                        + "cognom2 LIKE ? OR "
                        + "DNI LIKE ? OR "
                        + "numSS LIKE ?");

                statement = con.prepareStatement(sQuery);

                try {
                    statement.setString(1, "%" + sQuery + "%");
                    statement.setString(2, "%" + sQuery + "%");
                    statement.setString(3, "%" + sQuery + "%");
                    statement.setString(4, "%" + sQuery + "%");
                    statement.setString(5, "%" + sQuery + "%");

                } catch (SQLException ex) {
                    Logger.getLogger(BotonTablaInfoBuscar.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
        statement.executeQuery();
        
        return statement.getResultSet();
    }
}
