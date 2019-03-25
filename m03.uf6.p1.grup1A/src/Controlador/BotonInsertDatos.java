/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EnumTablas;
import Vista.CreaNuevo;
import Vista.FormularioInsert;
import static Vista.TablaInfo.itemCheck;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Cho_S
 */
public class BotonInsertDatos implements ActionListener {

        public BotonInsertDatos() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //Nuevo para crear
            switch (EnumTablas.getEnum(itemCheck)) {
                case Visita:
                    CreaNuevo frame = new CreaNuevo();
                    frame.setVisible(true);
                    break;
                case Malalties:
                    frame = new CreaNuevo();
                    frame.setVisible(true);
                    break;
                default:
                    FormularioInsert frameInsert = new FormularioInsert();
                    frameInsert.setVisible(true);
                    break;
            }
        }
    }