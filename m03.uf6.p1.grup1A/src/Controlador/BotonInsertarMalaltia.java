/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.PorcedimientosMalaltia;
import Vista.ErrorInsert;
import static Vista.NovaMalaltia.*;
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
public class BotonInsertarMalaltia extends MouseAdapter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        String[] data = new String[4];
        String errors = "";

        try {
            data[0] = JTxtFldNom.getText();
            data[2] = JTxtAreaTractament.getText();
            data[3] = JTxtFldDuradaTractament.getText();

            if (JCkBoxCausaBaixa.isSelected()) {
                data[1] = "true";
            } else {
                data[1] = "false";
            }

            if (PorcedimientosMalaltia.existeMalaltia(data[0])) {
                errors = "Ya existe una enfermedad con ese nombre!";
            } else {
            }
        } catch (NullPointerException e) {
            errors = "Los campos no pueden estar vacios!";
        } catch (SQLException ex) {
            Logger.getLogger(BotonInsertarMalaltia.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (errors.length() > 0) {
            ErrorInsert.infoBox(errors, "Error");
        }else{
            try {
                if (PorcedimientosMalaltia.insertarMalaltia(data)) {
                   ErrorInsert.infoBox("Datos insertados con Exito!", "Success"); 
                }else{
                     ErrorInsert.infoBox("No se han podido introducir los datos", "Error inesperado");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BotonInsertarMalaltia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
