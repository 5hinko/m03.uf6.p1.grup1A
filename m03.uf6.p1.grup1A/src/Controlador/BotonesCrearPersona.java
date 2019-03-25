/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.ErrorInsert;
import Modelo.ComprobacionesPersona;
import static Vista.FormularioInsert.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

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
                data[4] = JTxtFldNIF.getText();
                data[3] = JTxtFldNumeroSS.getText();
                data[5] = JTxtFldTelf.getText();
                data[6] = JTxtFldCiutat.getText();
                data[7] = JTxtFldCodiPostal.getText();
                data[8] = JTxtFldCarrer.getText();
            }

            errors = comprobaciones(data);
            ErrorInsert.infoBox(errors, "Error");

            //Un metodo para mirar en la base de datos si la clase primaria ya existe.
        } catch (NullPointerException e) {
            errors = "Los campos no pueden estar vacios.";
            fallo = true;
        }
        if (fallo) {
            ErrorInsert.infoBox(errors, "Error");
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
