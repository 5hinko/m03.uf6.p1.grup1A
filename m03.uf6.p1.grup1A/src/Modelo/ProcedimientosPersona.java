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

/**
 *
 * @author Lorenzo
 */
public class ProcedimientosPersona {

    public static Connection conn;

    public static boolean existePaciente(String DNI) throws SQLException {
        conn = Connexion.getConnection();
        CallableStatement statement = conn.prepareCall("{?=call existe_paciente(?)}");
        statement.registerOutParameter(1, Types.INTEGER);
        statement.setString(2, DNI);
        statement.execute();
        int numero = statement.getInt(1);
        return numero > 0;
    }

    public static boolean existeMedico(String DNI) throws SQLException {
        conn = Connexion.getConnection();
        CallableStatement statement = conn.prepareCall("{?=call existe_medico(?)}");
        statement.registerOutParameter(1, Types.INTEGER);
        statement.setString(2, DNI);
        statement.execute();
        int numero = statement.getInt(1);
        return numero > 0;
    }

    public static boolean existeSS(String SS) throws SQLException {
        conn = Connexion.getConnection();
        CallableStatement statement = conn.prepareCall("{?=call existeSS(?)}");
        statement.registerOutParameter(1, Types.INTEGER);
        statement.setString(2, SS);
        statement.execute();
        int numero = statement.getInt(1);
        return numero > 0;
    }
}
