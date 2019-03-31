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
        boolean commited = false;
        fila = e.getFirstRow();
        dades = jTablaInfo.getModel();
        try {
            //Actualiza Solo debería hacerlo el Admin
            con = Connexion.getConnectionAdmin();
            con.setAutoCommit(false);
            updateQueryPreparedStatement();
            con.commit();
            sentencia.executeUpdate();
            commited = true;
        } catch (SQLException ex) {
                Logger.getLogger(TablaInfoAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    if (!commited) {
                        con.rollback();
                    }
                    con.close();
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TablaInfoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updateQueryPreparedStatement() throws SQLException {
        EnumTablas tablaEnum = EnumTablas.getEnum(itemCheck);
        System.out.println("" + tablaEnum);
        String consulta = "UPDATE " + tablaEnum;
        switch (tablaEnum) {
            case Malalties:
                consulta += " SET nom LIKE ?, causaBaixa = ?, tratamiento LIKE ?, duradaTratamentDies = ? WHERE codi = ?";
                sentencia = con.prepareStatement(consulta);
                sentencia.setString(1, (String) (dades.getValueAt(fila, 1)));
                sentencia.setInt(2, Integer.parseInt(dades.getValueAt(fila, 2).toString()));
                sentencia.setString(3, (String) (dades.getValueAt(fila, 3)));
                sentencia.setInt(4, Integer.parseInt(dades.getValueAt(fila, 4).toString()));
                sentencia.setInt(5, Integer.parseInt(dades.getValueAt(fila, 0).toString()));
                break;
            case Visita:
                consulta += " SET nomMalaltia LIKE ?, dniMetges LIKE ? , informe LIKE ? WHERE dataVisita LIKE ? AND dniPacient LIKE ?";
                sentencia = con.prepareStatement(consulta);
                sentencia.setString(1, (String) (dades.getValueAt(fila, 1)));
                sentencia.setString(2, (String) (dades.getValueAt(fila, 3)));
                sentencia.setString(3, (String) (dades.getValueAt(fila, 4)));
                sentencia.setString(4, (String) (dades.getValueAt(fila, 0)));
                sentencia.setString(5, (String) (dades.getValueAt(fila, 2)));
                break;
            case Pacients:
                consulta += " SET nom LIKE ?, cognom1 LIKE ?, cognom2 LIKE ?, numSS LIKE ?, telefon LIKE ? , ciutat LIKE ?, codipostal = ?, direccio LIKE ? WHERE DNI LIKE ?";
                sentencia = con.prepareStatement(consulta);
                sentencia.setString(1, (String) (dades.getValueAt(fila, 0)));
                sentencia.setString(2, (String) (dades.getValueAt(fila, 1)));
                sentencia.setString(3, (String) (dades.getValueAt(fila, 2)));
                sentencia.setString(4, (String) (dades.getValueAt(fila, 4)));
                sentencia.setString(5, (String) (dades.getValueAt(fila, 5)));
                sentencia.setString(6, (String) (dades.getValueAt(fila, 6)));
                sentencia.setString(7, (String) (dades.getValueAt(fila, 7)));
                sentencia.setString(8, (String) (dades.getValueAt(fila, 8)));
                sentencia.setString(9, (String) (dades.getValueAt(fila, 3)));
                break;
            case Metges:
                consulta += " SET nom LIKE ?, cognom1 LIKE ?, cognom2 LIKE ?, numSS LIKE ?, telefon LIKE ?, ciutat LIKE ?, codipostal = ?, direccio LIKE ?, codiCC LIKE ?, salariMensual = ? WHERE DNI LIKE ?";
                sentencia = con.prepareStatement(consulta);
                sentencia.setString(1, (String) (dades.getValueAt(fila, 0)));
                sentencia.setString(2, (String) (dades.getValueAt(fila, 1)));
                sentencia.setString(3, (String) (dades.getValueAt(fila, 2)));
                sentencia.setString(4, (String) (dades.getValueAt(fila, 4)));
                sentencia.setString(5, (String) (dades.getValueAt(fila, 5)));
                sentencia.setString(6, (String) (dades.getValueAt(fila, 6)));
                sentencia.setString(7, (String) (dades.getValueAt(fila, 7)));
                sentencia.setString(8, (String) (dades.getValueAt(fila, 8)));
                sentencia.setString(9, (String) (dades.getValueAt(fila, 9)));
                sentencia.setInt(10, Integer.parseInt(dades.getValueAt(fila, 10).toString()));
                sentencia.setString(11, (String) (dades.getValueAt(fila, 3)));

                break;
            default:
                break;
        }
    }
}
