/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.*;
import java.sql.Date;
import Vista.ErrorInsert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo
 */
public abstract class InsertPersona {

    static String[] datos;

    public static void Crear(String[] datos) {
        Connection conn = null;
        boolean commit = false;
        /*
        try {
            conn = Connexion.getConnection();

            //Hacer lo del roll back.
            //Hacerlo con un for y getting el tamaño de a tabla
            String query = "INSERT INTO pacients VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement sentencia = conn.prepareStatement(query);

            for (int i = 0; i < datos.length; i++) {
                if (i == 7 ) {
                    sentencia.setInt(i++, Integer.parseInt(datos[i]));
                }
                sentencia.setString(i++, datos[i]);
            }

            sentencia.executeUpdate();
            conn.commit();
            commit = true;
            ErrorInsert.infoBox("Completado con ersito", "bien");
            
        } catch (Exception e) {
            System.out.println("-error");
        } finally {
            try {
                if (conn!= null) {
                    if (!commit) {
                        conn.rollback();
                    }
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Error más abajo");
            }
        }
         */

        String query = "INSERT INTO pacients"
                + " (nom,cognom1,cognom2,DNI,numSS,telefon,ciutat,codipostal,direccion)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";

        //Cambiar esto por un for
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, datos[0]);
            preparedStatement.setString(2, datos[1]);
            preparedStatement.setString(3, datos[2]);
            preparedStatement.setString(4, datos[3]);
            preparedStatement.setString(5, datos[4]);
            preparedStatement.setString(6, datos[5]);
            preparedStatement.setString(7, datos[6]);
            preparedStatement.setInt(8, Integer.parseInt(datos[6]));
            preparedStatement.setString(9, datos[7]);

            preparedStatement.executeUpdate();

            ErrorInsert.infoBox("Completado con ersito", "bien");

        } catch (SQLException ex) {
            Logger.getLogger(InsertPersona.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) throws SQLException, ParseException {
        Connection conn = Connexion.getConnection();
        byte a = 0;
       


        String query = "INSERT INTO malalties"
                + "(nom,causaBaixa,tratamiento,duradaTratamentDies)"
                + "VALUES (?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, "Tuberculosis");
        ps.setString(2, "Cama");
        ps.setInt(3, 4);

        ps.execute();

        conn.close();

    }

}

/*
INSERT INTO metges VALUES
('Dr.Paco','Ruíz','Millan','23415679N','12SDE34GFA4','663492256',
'Toledo',3948,'Pasadizo Esgargamellà, 232A',34,'EDASERD4537F49392459',3000),


CREATE TABLE metges
(
nom CHAR(15) NOT NULL,
cognom1 CHAR(20) NOT NULL,
cognom2 CHAR(20) NOT NULL,
DNI CHAR(9) PRIMARY KEY,
numSS CHAR(11) UNIQUE NOT NULL,
telefon CHAR(9) NOT NULL,
ciutat CHAR(20) NOT NULL,
codipostal INT(6),
direccion CHAR(30),
numEmpleat SMALLINT(2) UNIQUE,
codiCC CHAR(20),
salariMensual INT(10)
);
 */
