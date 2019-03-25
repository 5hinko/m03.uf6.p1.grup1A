/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.ErrorInsert;
import static Vista.FormularioInsert.*;
import Vista.TablaInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 *
 * @author Lorenzo
 */
public class BotonMalalties implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TablaInfo frame = new TablaInfo(3);
        frame.setVisible(true);
    }

}
