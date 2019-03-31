/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo
 */
public class PorcedimientosMalaltia {

    public static Connection conn = null;

    public static boolean existeMalaltia(String nombre) {
        try {
            conn = ConnexionUser.getConnection();
            CallableStatement statement = conn.prepareCall("{?=call existe_malaltia(?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, nombre);
            statement.execute();
            int numero = statement.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PorcedimientosMalaltia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PorcedimientosMalaltia.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    public static boolean insertarMalaltia(String[] data) {

        try {
            conn = ConnexionUser.getConnection();
            CallableStatement statement = conn.prepareCall("{?=call introducir_malaltia(?,?,?,?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, data[0]);
            if (data[1].equals("true")) {
                statement.setBoolean(3, true);
            } else {
                statement.setBoolean(3, false);
            }
            statement.setString(4, data[2]);
            statement.setInt(5, Integer.parseInt(data[3]));

            statement.execute();
            int numero = statement.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PorcedimientosMalaltia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PorcedimientosMalaltia.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }
}
