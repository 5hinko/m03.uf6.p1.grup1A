/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ProcedimientosVisita;
import Vista.ErrorInsert;
import static Vista.TablaInfo.selectAllInTablaRefresh;
import static Vista.VisitaNova.JCBoxMonths;
import static Vista.VisitaNova.jLblInformacionMalaltia;
import static Vista.VisitaNova.jLblInformacionMetge;
import static Vista.VisitaNova.jLblInformacionPacient;
import static Vista.VisitaNova.JTxtFldYear;
import static Vista.VisitaNova.JTxtFldDays;
import static Vista.VisitaNova.JTxtFldHour;
import static Vista.VisitaNova.JTxtFldMin;
import static Vista.VisitaNova.jComboCercaMalaltia;
import static Vista.VisitaNova.jComboCercaMetge;
import static Vista.VisitaNova.jComboCercaPacient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cho_S
 */
public class BotonVisitaNovaCrea implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String sErrorMostrar = "No s'ha pogut crear la visita; \n";
        boolean hayError = false;
        if (jLblInformacionMalaltia.getText().toString().length() < 0) {
            sErrorMostrar += "No hi ha malaltia.\n";
            hayError = true;
        } else if (jLblInformacionMetge.getText().toString().length() < 0) {
            sErrorMostrar += "No hi ha metge.\n";
            hayError = true;
        } else if (jLblInformacionPacient.getText().toString().length() < 0) {
            sErrorMostrar += "No hi ha pacient.\n";
            hayError = true;
        } else if (JTxtFldYear.getText().toString().length() < 0) {
            sErrorMostrar += "Error de data (Any).\n";
            hayError = true;
        } else if (JTxtFldDays.getText().toString().length() < 0) {
            sErrorMostrar += "Error de data (Dia).\n";
            hayError = true;
        } else if (JTxtFldHour.getText().toString().length() < 0) {
            sErrorMostrar += "Error de data (Hora).\n";
            hayError = true;
        } else if (JTxtFldMin.getText().toString().length() < 0) {
            sErrorMostrar += "Error de data (Minuts).\n";
            hayError = true;
        } else {
            try {
                Integer.parseInt(JTxtFldYear.getText().toString());
                Integer.parseInt(JTxtFldDays.getText().toString());
                Integer.parseInt(JTxtFldHour.getText().toString());
                Integer.parseInt(JTxtFldMin.getText().toString());

            } catch (NumberFormatException ex) {
                sErrorMostrar = "Introducció no vàlida.\n";
                hayError = true;
            }
            if (!hayError) {

                int mesJBox = JCBoxMonths.getSelectedIndex();
                String seleccionado = "01";
                switch (mesJBox) {
                    case 1:
                        seleccionado = "01";
                        break;
                    case 2:
                        seleccionado = "02";
                    case 3:
                        seleccionado = "03";
                        break;
                    case 4:
                        seleccionado = "04";
                        break;
                    case 5:
                        seleccionado = "05";
                        break;
                    case 6:
                        seleccionado = "06";
                        break;
                    case 7:
                        seleccionado = "07";
                        break;
                    case 8:
                        seleccionado = "08";
                        break;
                    case 9:
                        seleccionado = "09";
                        break;
                    case 10:
                        seleccionado = "10";
                        break;
                    case 11:
                        seleccionado = "11";
                        break;
                    case 12:
                        seleccionado = "12";
                        break;

                }

                String malaltia = jLblInformacionMalaltia.getText();
                String paciente = (String) jComboCercaPacient.getSelectedItem().toString();
                String medico = (String) jComboCercaMetge.getSelectedItem().toString();

                String fecha = (JTxtFldYear.getText() + "-" + seleccionado + "-" + JTxtFldDays.getText() + " " + JTxtFldHour.getText() + ":" + JTxtFldMin.getText() + ":00");
                String[] data = {fecha, malaltia, paciente, medico, "sdasd"};

                try {
                    if (ProcedimientosVisita.crearVisita(data)) {
                        ErrorInsert.infoBox("Datos insertados con Exito!", "Success");
                        selectAllInTablaRefresh();
                    } else {
                        ErrorInsert.infoBox("Ha ocurrido un error inesperado", "Error");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BotonVisitaNovaCrea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }///asdasdas

        if (hayError) {
            ErrorInsert.infoBox(sErrorMostrar, "Notificació");
        } else {
            //Crea Visita

            //RollBack
        }
    }

}
