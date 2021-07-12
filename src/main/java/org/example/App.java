package org.example;

import javax.swing.*;

import entity.Klient;
import entity.Ucet;
import enums.Narodnost;
import enums.TypUctu;
import gui.HlavneOkno;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            System.out.println(e.getMessage());
        }

        new HlavneOkno();
    }
}
