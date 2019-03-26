package Modelo;

import Vista.TablaInfo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloDeTablaSimple extends AbstractTableModel {

    private ArrayList<String> nombreCampos;
    private ArrayList<ArrayList> registros;

    public ModeloDeTablaSimple(ResultSet results) {
        super();
        try {
            ResultSetMetaData rsm = results.getMetaData();
            int numCampos = rsm.getColumnCount();
            nombreCampos = new ArrayList();
            for (int i = 1; i <= numCampos; i++) {
                nombreCampos.add(rsm.getColumnName(i));
            }
            registros = new ArrayList();
            while (results.next()) {
                ArrayList<String> campos = new ArrayList();
                for (int j = 1; j <= numCampos; j++) {
                    campos.add(results.getString(j));
                }
                registros.add(campos);
            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        return nombreCampos.size();
    }

    @Override
    public int getRowCount() {
        return registros.size();
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        ArrayList aux = registros.get(row);
        aux.set(col, (String) value);
        //Metodo que actualiza
        fireTableCellUpdated(row, col);
    }

    @Override
    public String getColumnName(int col) {
        return nombreCampos.get(col);
    }

    @Override
    public Class getColumnClass(int col) {
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return registros.get(row).get(col);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        switch (EnumTablas.getEnum(TablaInfo.itemCheck)) {
            case Malalties:
                if (columnIndex == 0) {
                    return false;
                }
                break;
            case Metges:
                if (columnIndex == 3) {
                    return false;
                }
                break;
            case Pacients:
                if (columnIndex == 3) {
                    return false;
                }
                break;
            case Visita:
                if (columnIndex == 0 || columnIndex == 2) {
                    return false;
                }
                break;
        }
        return true;
    }

}
