/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ComprobacionesPersona;
import Modelo.ProcedimientosPersona;
import Vista.ErrorInsert;
import static Vista.FormularioInsert.*;
import static Vista.TablaInfo.selectAllInTablaRefresh;
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

        if (ae.getSource() == JBtnCrea) {

            String[] data = null;
            String errors = "";
            boolean fallo = false;
            try {
                if (clase == 0) {
                    data = new String[11];
                    data[0] = JTxtFldNom.getText();
                    data[1] = JTxtFldPrimerCognom.getText();
                    data[2] = JTxtFldSegonCognom.getText();
                    data[3] = JTxtFldNIF.getText();
                    data[4] = JTxtFldNumeroSS.getText();
                    data[5] = JTxtFldTelf.getText();
                    data[6] = JTxtFldCiutat.getText();
                    data[7] = JTxtFldCodiPostal.getText();
                    data[8] = JTxtFldCarrer.getText();
                    data[9] = JTxtFldCompteCorrent.getText();
                    data[10] = JTxtFldSalariMensual.getText();
                } else if (clase == 1) {
                    data = new String[9];
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
                errors += comprobaciones(data);

            } catch (NullPointerException e) {
                errors = "Los campos no pueden estar vacios.";
            }

            if (errors.length() > 0) {
                ErrorInsert.infoBox(errors, "Error");
            } else {
                if (ProcedimientosPersona.existePaciente(data[3])) {
                    ErrorInsert.infoBox("Ya existe un paciente/medico con ese DNI", "Error");
                } else {
                    if (ProcedimientosPersona.insertarPaciente(data)) {
                        ErrorInsert.infoBox("Datos insertados con Exito!", "Success");
                        limpiartexto();
                        selectAllInTablaRefresh();
                        //JFwindow.dispose();
                    } else {
                        ErrorInsert.infoBox("No se han podido introducir los datos", "Error inesperado");
                        //JFwindow.dispose();
                    }
                }
            }
        }
    }

    public String comprobaciones(String[] data) {
        //DNI
        String error = "";
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

    public void limpiartexto() {
        JTxtFldNom.setText("");
        JTxtFldPrimerCognom.setText("");
        JTxtFldSegonCognom.setText("");
        JTxtFldNIF.setText("");
        JTxtFldNumeroSS.setText("");
        JTxtFldTelf.setText("");
        JTxtFldCiutat.setText("");
        JTxtFldCodiPostal.setText("");
        JTxtFldCarrer.setText("");
        if (data.length > 10) {
            JTxtFldCompteCorrent.setText("");
            JTxtFldSalariMensual.setText("");

        }

    }
}
