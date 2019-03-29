/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EnumTablas;
import Vista.FormularioInsert;
import static Vista.TablaInfo.itemCheck;
import Vista.*;
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
                    VisitaNova frameVisita = new VisitaNova();
                    frameVisita.setVisible(true);
                    break;
                case Malalties:
                    NovaMalaltia frameMalaltia = new NovaMalaltia();
                    frameMalaltia.setVisible(true);
                    break;
                default:
                    FormularioInsert frameInsert = new FormularioInsert(itemCheck);
                    frameInsert.setVisible(true);
                    break;
            }
        }
    }
