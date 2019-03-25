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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author Cho_S
 */
public class BotonBuscar implements ActionListener {

    public BotonBuscar() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String sQuery = jTxtFieldBusca.getText().toString();
        if (sQuery.length() > 0) {
            ResultSet resultat = selectQuery(sQuery);
            TableModel model = new ModeloDeTablaSimple(resultat);
            actualitzaTaula(jTablaInfo, model);
        } else {
            TablaInfo.cambiosEnComboBox();
        }
    }

    private ResultSet selectQuery(String sBuscar) {
        ResultSet resultat = null;
        String sQuery;
        EnumTablas tablaEnum = EnumTablas.getEnum(itemCheck);
        switch (tablaEnum) {
            case Malalties:
                sQuery = ("SELECT * "
                        + " FROM " + tablaEnum
                        + " WHERE codi = " + sBuscar + " OR "
                        + "nom LIKE '%" + sBuscar + "%' OR "
                        + "tratamiento LIKE '%" + sBuscar + "%' OR "
                        + "duradaTratamentDies = " + sBuscar);
                break;
            case Visita:
                sQuery = ("SELECT * "
                        + " FROM " + tablaEnum
                        + " WHERE dataVisita '%" + sBuscar + "%' OR "
                        + "nomMalaltia '%" + sBuscar + "%' OR "
                        + "dniPacient '%" + sBuscar + "%' OR "
                        + "dniMetges '%" + sBuscar + "%'");
                break;
            default:
                sQuery = ("SELECT * "
                        + " FROM " + tablaEnum
                        + " WHERE nom LIKE '%" + sBuscar + "%' OR "
                        + "cognom1 LIKE '%" + sBuscar + "%' OR "
                        + "cognom2 LIKE '%" + sBuscar + "%' OR "
                        + "DNI LIKE '%" + sBuscar + "%' OR "
                        + "numSS LIKE '%" + sBuscar + "%'");
                break;
        }
        try {
            resultat = Connexion.consultaBBDD(sQuery);
        } catch (SQLException ex) {
            Logger.getLogger(TablaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }
}
