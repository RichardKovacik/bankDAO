package model;

import entity.Klient;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KlientModel extends AbstractTableModel {
    private List<Klient> zoznamKlientov;
    public static String[] nazvyStlpcov = {"ID","Meno", "Priezvisko", "Vek", "Narodnost"};

    public KlientModel(List<Klient> zoznamKlientov) {
        this.zoznamKlientov = zoznamKlientov;
    }

    @Override
    public int getRowCount() {
        return this.zoznamKlientov.size();
    }

    @Override
    public int getColumnCount() {
        return this.nazvyStlpcov.length;
    }

    @Override
    public String getColumnName(int column) {
        return  this.nazvyStlpcov[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klient klient = this.zoznamKlientov.get(rowIndex);

        switch (columnIndex){
            case 0 : return klient.getId();
            case 1 : return klient.getMeno();
            case 2 : return klient.getPriezvisko();
            case 3 : return klient.getVek();
            case 4 : return klient.getNarodnost().getReprezentacia();
        }
        return null;
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.getClass();
    }
}
