package model;

import entity.Klient;
import entity.Ucet;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UcetModel extends AbstractTableModel {
    private List<Ucet> zoznamUctov;
    public static String[] nazvyStlpcov = {"ID_Majitela","Cislo_Uctu", "Typ_Uctu", "Mnozstvo_Penazi"};

    public UcetModel(List<Ucet> zoznamUctov) {
        this.zoznamUctov = zoznamUctov;
    }

    @Override
    public int getRowCount() {
        return this.zoznamUctov.size();
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
        Ucet ucet = this.zoznamUctov.get(rowIndex);

        switch (columnIndex){
            case 0 : return ucet.getKlient().getId();
            case 1 : return ucet.getCisloUctu();
            case 2 : return ucet.getTyp().getReprezentacia();
            case 3 : return ucet.getMnozstvoPenazi();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.getClass();
    }
}
