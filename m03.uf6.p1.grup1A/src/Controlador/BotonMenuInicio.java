/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EnumTablas;
import static Vista.MenuInici.jBtnMalalties;
import static Vista.MenuInici.jBtnMetges;
import static Vista.MenuInici.jBtnPacients;
import static Vista.MenuInici.jBtnVisitas;
import Vista.TablaInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Lorenzo
 */
public class BotonMenuInicio implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TablaInfo frame = new TablaInfo(0);
        if (e.getSource() == jBtnMalalties) {
            frame = new TablaInfo(EnumTablas.Malalties.getNum());
        } else if (e.getSource() == jBtnMetges) {
            frame = new TablaInfo(EnumTablas.Metges.getNum());
        } else if (e.getSource() == jBtnPacients) {
            frame = new TablaInfo(EnumTablas.Pacients.getNum());
        } else if (e.getSource() == jBtnVisitas) {
            frame = new TablaInfo(EnumTablas.Visita.getNum());
        }
        frame.setVisible(true);
    }

}
