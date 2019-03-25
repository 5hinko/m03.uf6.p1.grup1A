/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Connexion;
import Modelo.EnumTablas;
import Vista.TablaInfo;
import static Vista.TablaInfo.itemCheck;
import static Vista.TablaInfo.jTablaInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Cho_S
 */
public class TablaInfoAction implements TableModelListener {

    public TablaInfoAction() {
    }
    
    private int fila;
    private PreparedStatement sentencia = null;
    private Connection con = null;
    private TableModel dades;

    @Override
    public void tableChanged(TableModelEvent e) {
        fila = e.getFirstRow();
        dades = jTablaInfo.getModel();
        try {
            con = Connexion.getConnection();
            updateQuery();
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TablaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateQuery() {
        try {
            EnumTablas tablaEnum = EnumTablas.getEnum(itemCheck);
            String consulta = "UPDATE " + tablaEnum;
            switch (tablaEnum) {
                case Malalties:
                    consulta += " SET nom = ?, causaBaixa = ?, tratamiento = ?, duradaTratamentDies = ? WHERE codi = ?";
                    sentencia = con.prepareStatement(consulta);
                    sentencia.setString(1, (String) (dades.getValueAt(fila, 1)));
                    sentencia.setInt(2, Integer.parseInt(dades.getValueAt(fila, 2).toString()));
                    sentencia.setString(3, (String) (dades.getValueAt(fila, 3)));
                    sentencia.setInt(4, Integer.parseInt(dades.getValueAt(fila, 4).toString()));
                    sentencia.setInt(5, Integer.parseInt(dades.getValueAt(fila, 0).toString()));
                    break;
                case Visita:
                    consulta += " SET nomMalaltia = ?, dniMetges = ? , informe = ? WHERE dataVisita = ? AND dniPacient = ?";
                    sentencia = con.prepareStatement(consulta);
                    sentencia.setString(1, (String) (dades.getValueAt(fila, 1)));
                    sentencia.setString(2, (String) (dades.getValueAt(fila, 3)));
                    sentencia.setString(3, (String) (dades.getValueAt(fila, 4)));
                    sentencia.setString(4, (String) (dades.getValueAt(fila, 0)));
                    sentencia.setString(5, (String) (dades.getValueAt(fila, 2)));
                    break;
                case Pacients:
                    consulta += " SET nom = ?, cognom1 = ?, cognom2 = ?, numSS = ?, telefon = ? , ciutat = ?, codipostal= ?, direccio = ? WHERE DNI = ?";
                    sentencia = con.prepareStatement(consulta);
                    sentencia.setString(1, (String) (dades.getValueAt(fila, 0)));
                    sentencia.setString(2, (String) (dades.getValueAt(fila, 1)));
                    sentencia.setString(3, (String) (dades.getValueAt(fila, 2)));
                    sentencia.setString(4, (String) (dades.getValueAt(fila, 4)));
                    sentencia.setString(5, (String) (dades.getValueAt(fila, 5)));
                    sentencia.setString(6, (String) (dades.getValueAt(fila, 6)));
                    sentencia.setInt(7, Integer.parseInt(dades.getValueAt(fila, 7).toString()));
                    sentencia.setString(8, (String) (dades.getValueAt(fila, 8)));
                    sentencia.setString(6, (String) (dades.getValueAt(fila, 3)));
                    break;
                case Metges:
                    consulta += " SET nom = ?, cognom1 = ?, cognom2 = ?, numSS = ?, telefon = ?, ciutat = ?, codipostal = ?, direccio = ?, numEmpleat = ?, codiCC= ?, salariMensual = ? WHERE DNI = ?";
                    sentencia = con.prepareStatement(consulta);
                    sentencia.setString(1, (String) (dades.getValueAt(fila, 0)));
                    sentencia.setString(2, (String) (dades.getValueAt(fila, 1)));
                    sentencia.setString(3, (String) (dades.getValueAt(fila, 2)));
                    sentencia.setString(4, (String) (dades.getValueAt(fila, 4)));
                    sentencia.setString(5, (String) (dades.getValueAt(fila, 5)));
                    sentencia.setString(6, (String) (dades.getValueAt(fila, 6)));
                    sentencia.setInt(7, Integer.parseInt(dades.getValueAt(fila, 7).toString()));
                    sentencia.setString(8, (String) (dades.getValueAt(fila, 8)));
                    sentencia.setInt(9, Integer.parseInt(dades.getValueAt(fila, 9).toString()));
                    sentencia.setString(10, (String) (dades.getValueAt(fila, 10)));
                    sentencia.setInt(11, Integer.parseInt(dades.getValueAt(fila, 11).toString()));
                    sentencia.setString(12, (String) (dades.getValueAt(fila, 3)));

                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
