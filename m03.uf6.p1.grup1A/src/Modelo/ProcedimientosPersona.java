/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo
 */
public class ProcedimientosPersona {

    public static Connection conn = null;

    public static boolean existePaciente(String DNI) {
        try {
            conn = ConnexionUser.getConnection();
            CallableStatement statement = conn.prepareCall("{?=call existe_paciente(?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, DNI);
            statement.execute();
            int numero = statement.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProcedimientosPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProcedimientosPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    public static boolean existeMedico(String DNI) {
        try {
            conn = ConnexionUser.getConnection();
            CallableStatement statement = conn.prepareCall("{?=call existe_medico(?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, DNI);
            statement.execute();
            int numero = statement.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProcedimientosPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProcedimientosPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    public static boolean existeSS(String SS) {
        try {
            conn = ConnexionUser.getConnection();
            CallableStatement statement = conn.prepareCall("{?=call existeSS(?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, SS);
            statement.execute();
            int numero = statement.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProcedimientosPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProcedimientosPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            return false;
    }

    public static void main(String[] args) {
    }

    //Hacer un if data para saber si es medico o paciente.
    public static boolean insertarPaciente(String[] data) {

        try {
            conn = ConnexionUser.getConnection();
            if (data.length == 9) {
                CallableStatement statement = conn.prepareCall("{?=call introducir_paciente(?,?,?,?,?,?,?,?,?)}");
                statement.registerOutParameter(1, Types.INTEGER);
                statement.setString(2, data[0]);
                for (int i = 0; i < data.length; i++) {
                    statement.setString(i + 2, data[i]);
                }
                statement.execute();
                int numero = statement.getInt(1);
                return numero > 0;
            } else {
                CallableStatement statement = conn.prepareCall("{?=call introducir_medico(?,?,?,?,?,?,?,?,?,?,?)}");
                statement.registerOutParameter(1, Types.INTEGER);
                statement.setString(2, data[0]);
                for (int i = 0; i < data.length; i++) {
                    if (i == 11) {
                        statement.setInt(i + 2, Integer.parseInt(data[i]));
                    } else {
                        statement.setString(i + 2, data[i]);
                    }
                }
                statement.execute();
                int numero = statement.getInt(1);
                return numero > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcedimientosPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProcedimientosPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

    }
}
