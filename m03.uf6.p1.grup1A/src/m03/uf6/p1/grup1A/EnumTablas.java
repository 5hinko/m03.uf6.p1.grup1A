/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf6.p1.grup1A;

/**
 *
 * @author Cho_S
 */
public enum EnumTablas {
    Metges(0),
    Pacients(1),
    Visita(2),
    Malalties(3);

    final private int num;

    EnumTablas(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public static EnumTablas getEnum(int num) {
        for (EnumTablas tabla : EnumTablas.values()) {
            if (tabla.getNum() == num) {
                return tabla;
            }
        }
        return null;
    }

}
