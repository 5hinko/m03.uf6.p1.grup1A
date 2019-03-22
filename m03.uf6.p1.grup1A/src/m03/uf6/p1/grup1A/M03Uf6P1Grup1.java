/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf6.p1.grup1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;


/**
 *
 * @author Lorenzo
 */
public class M03Uf6P1Grup1 {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = Connexion.getConnection();
        
Statement sentencia = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            sentencia.executeQuery("select * from metges");
            ResultSet resultado = sentencia.getResultSet();
            while (resultado.next()) {
                String dniMedico = resultado.getString("DNI");
                String nom = resultado.getString("nom");
                System.out.println("Id: " + dniMedico + " nom: " + nom );
                
            }
            /*
        nom CHAR(15) NOT NULL,
cognom1 CHAR(20) NOT NULL,
cognom2 CHAR(20) NOT NULL,
DNI CHAR(9) PRIMARY KEY,
numSS CHAR(11) UNIQUE NOT NULL,
telefon CHAR(9) NOT NULL,
numEmpleat SMALLINT(2) UNIQUE,
codiCC CHAR(20),
salariMensual INT(10)
*/
    }

}
