/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.PorcedimientosMalaltia;
import Vista.ErrorInsert;
import static Vista.NovaMalaltia.*;
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
public class BotonInsertarMalaltia extends MouseAdapter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        String[] data = new String[4];
        String errors = "";
//Vacios
        try {
            data[0] = JTxtFldNom.getText();
            data[2] = JTxtAreaTractament.getText();
            data[3] = JTxtFldDuradaTractament.getText();
            if (JCkBoxCausaBaixa.isSelected()) {
                data[1] = "true";
            } else {
                data[1] = "false";
            }

            try {
                int a = Integer.parseInt(data[3]);
            } catch (NumberFormatException e) {
                errors += "Num. Dies debe ser un Integer";
            }

            for (String string : data) {
                if (string.length() == 0) {
                    errors += "Los campos no pueden estar vacios!";
                }
            }

            if (PorcedimientosMalaltia.existeMalaltia(data[0])) {
                errors += "Ya existe una enfermedad con ese nombre!";
            }
        } catch (NullPointerException e) {
            errors += "Los campos no pueden estar vacios!";
        }
        if (errors.length() > 0) {
            ErrorInsert.infoBox(errors, "Error");
        } else {
                if (PorcedimientosMalaltia.insertarMalaltia(data)) {
                    ErrorInsert.infoBox("Datos insertados con Exito!", "Success");
                    limpiar();
                    selectAllInTablaRefresh();
                } else {
                    ErrorInsert.infoBox("No se han podido introducir los datos", "Error inesperado");
                }
        }
    }

    public void limpiar() {
        JTxtFldNom.setText("");
        JTxtAreaTractament.setText("");
        JTxtFldDuradaTractament.setText("");
        JCkBoxCausaBaixa.setSelected(false);
    }

}
