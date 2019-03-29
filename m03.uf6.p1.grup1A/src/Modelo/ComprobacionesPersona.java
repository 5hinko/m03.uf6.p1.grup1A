/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lorenzo
 */
public abstract class ComprobacionesPersona {

    protected String nom;
    protected String cognom1;
    protected String cognom2;
    protected String numSegSocial;
    protected String nif;
    protected String telf;

    public static String corresponAlfabet(String text, String camp) {
        String regla;
        if (camp.equals("nom")) {
            regla = "[a-zA-ZáÁéÉíÍóÓúÚàÀèÈòÒïÏüÜñÑçÇ ]+";
        } else {
            regla = "[a-zA-ZáÁéÉíÍóÓúÚàÀèÈòÒïÏüÜ'ñÑçÇ ]+";
        }
        Pattern patro = Pattern.compile(regla);
        Matcher comparador = patro.matcher(text);
        String error = "";
        if (text.isEmpty()) {
            error += "Error, el campo del " + camp + " no puede estar vacio\n";
        }
        else if (comparador.matches() == false) {
            error = "El campo " + camp + " contiene los siguientes "
                    + "carácters inválidos >>";
            for (int i = 0; i < text.length(); i++) {
                String subText = text.substring(i, i + 1);
                comparador = patro.matcher(subText);
                if (comparador.matches() == false) {
                    error += subText;
                }
            }
            error += "<< .\n";
        }

        return error;
    }

    public static String comprovaNumSegSocial(String num) {
        String regla = "[0-9]+";
        Pattern patro = Pattern.compile(regla);
        Matcher comparador = patro.matcher(num);
        String error = "";
        if (num.length() != 12) {
            error += "El campo número de la seguridad social "
                    + "no tiene una longitud de 12 números.\n ";
        } else if (comparador.matches() == false) {
            error += "El campo número de la seguridad social no "
                    + "está conformado por números.\n ";
        } else if ((num.length() == 12) & (comparador.matches() == true)) {
            String control = num.substring(10, 12) + ".0";
            Double x = Double.valueOf(num.substring(0, 10));
            String provincia;

            x = x % 97;

            if (x > 0 & x < 10) {
                provincia = "0" + Double.toString(x);
            } else {
                provincia = Double.toString(x);
            }

            if (x > 66) {
                error += "El campo número de la seguridad social"
                        + " assenyala a una provincia "
                        + "inexistent, no és valid.\n";
            } else if (!provincia.equals(control)) {
                error += "El campo del número de la seguridad social"
                        + " no tiene una combinación de números válidos.\n";
            }
        }

        return error;
    }

    //Comentatrio
    public static String comprovaTelf(String telf) {
        String regla = "[0-9]+";
        Pattern patro = Pattern.compile(regla);
        Matcher comparador = patro.matcher(telf);
        String error = "";

        if (telf.length() != 9) {
            error += "El Número de teléfono tiene que constar de 9 números.\n";
        }

        if (comparador.matches() == false) {
            error += "El Número de teléfono tiene que constar de 9 números.\n";
        } else {
            if (Integer.parseInt(telf.substring(0, 1)) < 6) {
                error += "El campo número de telèfon tiene que ser un"
                        + " número nacional.\n";
            }
        }

        return error;
    }

    public static String comprovaNif(String nif) {
        String lletres = "TRWAGMYFPDXBNJZSQVHLCKE";
        String regla = "[0-9]{8}[" + lletres + "]";
        Pattern patro = Pattern.compile(regla);
        Matcher comparador = patro.matcher(nif);
        String error = "";
        if (comparador.matches() == true) {

            String numeros = nif.substring(0, 8);
            numeros += lletres.charAt(Integer.parseInt(numeros) % 23);

            if (!nif.equals(numeros)) {
                error += "El campo NIF no es valido, los digitos "
                        + "no corresponden con la letra.\n";
            }
        } else {
            error += "El campo NIF no presenta un format válido.\n";
        }

        return error;
    }
}
