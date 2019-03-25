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
        }
        else {
                regla = "[a-zA-ZáÁéÉíÍóÓúÚàÀèÈòÒïÏüÜ'ñÑçÇ ]+";
        }
        Pattern patro = Pattern.compile(regla);
        Matcher comparador = patro.matcher(text);
        String error = "";

        
         if (comparador.matches() == false) {
            error = "El camp " + camp + " conté els següents "
                    + "caràcters invàlids >>";
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
        if (num.isEmpty()) {
            error += "El camp número de la seguritat social "
                    + "no pot estar buit.\n";
        } else if (num.length() != 12) {
            error += "El camp número de la seguritat social "
                    + "no precisa d'una longitud de 12 números.\n ";
        } else if (comparador.matches() == false) {
            error += "El camp número de la seguritat social no "
                    + "està conformat per números.\n ";
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
                error += "El camp número de la seguritat social"
                        + " assenyala a una provincia "
                        + "inexistent, no és valid.\n";
            } else if (!provincia.equals(control)) {
                error += "El camp número de la seguritat social"
                        + " no disposa d'una combinació de"
                        + " números vàlida.\n";
            }
        }

        return error;
    }

    public static  String comprovaTelf(String telf) {
        String regla = "[0-9]+";
        Pattern patro = Pattern.compile(regla);
        Matcher comparador = patro.matcher(telf);
        String error = "";

        if (telf.length() != 9) {
            error += "El camp número de telèfon no precisa"
                    + " d'una longitud de 9 digits.\n";
        }

        if (comparador.matches() == false) {
            error += "El camp número de telèfon no està"
                    + " conformat únicament per números.\n";
        } else {
            if (Integer.parseInt(telf.substring(0, 1)) < 6) {
                error += "El camp número de telèfon ha de ser un"
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
        if (nif.isEmpty()) {
            error += "El camp NIF no pot estar buit.\n";
        } else if (comparador.matches() == true) {

            String numeros = nif.substring(0, 8);
            numeros += lletres.charAt(Integer.parseInt(numeros) % 23);

            if (!nif.equals(numeros)) {
                error += "El camp NIF no és valid, els digits "
                        + "no corresponen amb la lletra.\n";
            }
        } else {
            error += "El camp NIF no disposa del format adequat.\n";
        }

        return error;
    } 
}

