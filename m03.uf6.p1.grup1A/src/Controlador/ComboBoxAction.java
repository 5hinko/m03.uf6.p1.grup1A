/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EnumTablas;
import Vista.TablaInfo;
import static Vista.TablaInfo.itemCheck;
import static Vista.TablaInfo.jCombo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Cho_S
 */
public class ComboBoxAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //Comienza en 1
        itemCheck = EnumTablas.valueOf(jCombo.getSelectedItem().toString()).getNum();
        TablaInfo.cambiosEnComboBox();
    }
}
