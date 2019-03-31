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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lorenzo
 */
public class ProcedimientosVisita {

    public static Connection conn;

    public static boolean crearVisita(String[] data) {
        try {
            conn = ConnexionUser.getConnection();
            CallableStatement statement = conn.prepareCall("{?=call introducir_visita(?,?,?,?,?)}");
            Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[0]);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setDate(2, sqlDate);
            statement.setString(3, data[1]);
            statement.setString(4, data[2]);
            statement.setString(5, data[3]);
            statement.setString(6, data[4]);
            
            statement.execute();
            int numero = statement.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProcedimientosVisita.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProcedimientosVisita.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }
}
