/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Vista.TablaInfo.jBtnBusca;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Cho_S
 */
public class FieldBuscar extends KeyAdapter {

        public FieldBuscar() {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e); //To change body of generated methods, choose Tools | Templates.
            
            jBtnBusca.doClick();
            
            /*
            char c = e.getKeyChar();
            if (Character.isLetterOrDigit(c) || e.getKeyCode() == KeyEvent.VK_ENTER) {
            jBtnBusca.doClick();
            } else if (jTxtFieldBusca.getText().toString().length() > 0 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            jBtnBusca.doClick();
            } else {
            }
            */
        }
    }