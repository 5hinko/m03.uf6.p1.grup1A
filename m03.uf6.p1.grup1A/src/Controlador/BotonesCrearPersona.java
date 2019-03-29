/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ComprobacionesPersona;
import Modelo.InsertPersona;
import Modelo.ProcedimientosPersona;
import Vista.ErrorInsert;
import static Vista.FormularioInsert.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo
 */
public class BotonesCrearPersona extends MouseAdapter implements ActionListener {

    int clase;

    public BotonesCrearPersona(int clase) {
        this.clase = clase;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String[] data = null;
        String errors = null;
        boolean fallo = false;
        try {
            if (clase == 0) {
                data = new String[15];
                data[0] = JTxtFldNom.getText();
                data[1] = JTxtFldPrimerCognom.getText();
                data[2] = JTxtFldSegonCognom.getText();
                data[3] = JTxtFldNIF.getText();
                data[4] = JTxtFldNumeroSS.getText();
                data[5] = JTxtFldTelf.getText();
                data[6] = JTxtFldCiutat.getText();
                data[7] = JTxtFldCodiPostal.getText();
                data[8] = JTxtFldCarrer.getText();
                data[9] = JTxtFldNumEmpleat.getText();
                data[10] = JTxtFldCompteCorrent.getText();
                data[11] = JTxtFldSalariMensual.getText();
            } else if (clase == 1) {
                data = new String[12];
                data[0] = JTxtFldNom.getText();
                data[1] = JTxtFldPrimerCognom.getText();
                data[2] = JTxtFldSegonCognom.getText();
                data[3] = JTxtFldNIF.getText();
                data[4] = JTxtFldNumeroSS.getText();
                data[5] = JTxtFldTelf.getText();
                data[6] = JTxtFldCiutat.getText();
                data[7] = JTxtFldCodiPostal.getText();
                data[8] = JTxtFldCarrer.getText();
            }

            //Un metodo para mirar en la base de datos si la clase primaria ya existe.
            errors += comprobaciones(data);

        } catch (NullPointerException e) {
            errors = "Los campos no pueden estar vacios.";
            fallo = true;
            ErrorInsert.infoBox(errors, "ERROR");
        }

        try {
            /* if (fallo||errors != null) {
            ErrorInsert.infoBox(errors, "Error");
            } else {
            
            ErrorInsert.infoBox("muy bien", "Bien");
            }
             */
            if (ProcedimientosPersona.existePaciente(data[3])) {
                ErrorInsert.infoBox("Ya existe un paciente/medico con ese DNI", "Error");
            } else if (ProcedimientosPersona.existeSS(data[4])) {
                ErrorInsert.infoBox("Ya existe un paciente/medico con ese numero de SS", "Error");
            } else {
                ErrorInsert.infoBox("Bien", "Error");
            }

            /*
            try {
            GestioHospital.controladorCreaPersona(data, mode);

            new Mostra("CORRECTE", "La persona s'ha afegit correctament.");
            JFwindow.dispose();
            } catch (Exception e) {
            new Mostra("ERROR", "No s'ha pogut afegir la persona per les següents raons:" + e.getMessage());
            }
             */
        } catch (SQLException ex) {
            Logger.getLogger(BotonesCrearPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String comprobaciones(String[] data) {
        //DNI
        String error = "";
        //Quitar la comprbacion de que esten vacios
        error += ComprobacionesPersona.corresponAlfabet(data[0], "nom");
        error += ComprobacionesPersona.corresponAlfabet(data[1], "apellido");
        error += ComprobacionesPersona.corresponAlfabet(data[2], "segundo apellido");
        error += ComprobacionesPersona.comprovaNif(data[3]);
        error += ComprobacionesPersona.comprovaNumSegSocial(data[4]);
        error += ComprobacionesPersona.comprovaTelf(data[5]);

        data = new String[12];
        data[0] = JTxtFldNom.getText();
        data[1] = JTxtFldPrimerCognom.getText();
        data[2] = JTxtFldSegonCognom.getText();
        data[3] = JTxtFldNIF.getText();
        data[4] = JTxtFldNumeroSS.getText();
        data[5] = JTxtFldTelf.getText();
        data[6] = JTxtFldCiutat.getText();
        data[7] = JTxtFldCodiPostal.getText();
        data[8] = JTxtFldCarrer.getText();

        return error;
    }

}
