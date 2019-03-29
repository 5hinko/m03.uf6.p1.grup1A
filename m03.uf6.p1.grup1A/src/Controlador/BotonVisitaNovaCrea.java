/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.ErrorInsert;
import static Vista.VisitaNova.jLblInformacionMalaltia;
import static Vista.VisitaNova.jLblInformacionMetge;
import static Vista.VisitaNova.jLblInformacionPacient;
import static Vista.VisitaNova.JTxtFldYear;
import static Vista.VisitaNova.JTxtFldDays;
import static Vista.VisitaNova.JTxtFldHour;
import static Vista.VisitaNova.JTxtFldMin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Cho_S
 */
public class BotonVisitaNovaCrea implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String sErrorMostrar = "No s'ha pogut crear pels següents raóns; \n";
        boolean hayError = false;
        if (jLblInformacionMalaltia.getText().toString().length() > 0) {
            sErrorMostrar += "No hi ha malaltia.\n";
            hayError = true;
        } else if (jLblInformacionMetge.getText().toString().length() > 0) {
            sErrorMostrar += "No hi ha metge.\n";
            hayError = true;
        } else if (jLblInformacionPacient.getText().toString().length() > 0) {
            sErrorMostrar += "No hi ha pacient.\n";
            hayError = true;
        } else if (JTxtFldYear.getText().toString().length() > 0) {
            sErrorMostrar += "No existeix any.\n";
            hayError = true;
        } else if (JTxtFldDays.getText().toString().length() > 0) {
            sErrorMostrar += "Falta el dia de la Visita.\n";
            hayError = true;
        } else if (JTxtFldHour.getText().toString().length() > 0) {
            sErrorMostrar += "Falta l'hora de la visita.\n";
            hayError = true;
        } else if (JTxtFldMin.getText().toString().length() > 0) {
            sErrorMostrar += "Falta els minuts.\n";
            hayError = true;
        } else {
            try {
                Integer.parseInt(JTxtFldYear.getText().toString());
                Integer.parseInt(JTxtFldDays.getText().toString());
                Integer.parseInt(JTxtFldHour.getText().toString());
                Integer.parseInt(JTxtFldMin.getText().toString());

            } catch (Exception ex) {
                sErrorMostrar = "No és vàlid la introducció de números.\n";
                hayError = true;
            }
        }

        if (hayError) {
            ErrorInsert.infoBox(sErrorMostrar, "Notificació");
        } else {
            //Crea Visita

            //RollBack
        }
    }

}
