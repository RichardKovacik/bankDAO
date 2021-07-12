package gui;

import entity.Klient;
import entity.Ucet;
import enums.TypUctu;

import javax.swing.*;
import java.awt.event.*;

public class UcetEdit extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField cisloUctuField;
    private JTextField mnozstvoPenaziField;
    private JComboBox typUctuComboBox;
    private Ucet ucet;
    private Klient klient;
    private HlavneOkno hlavneOkno;

    public UcetEdit(HlavneOkno hlavneOkno) {
        this.hlavneOkno = hlavneOkno;
        setContentPane(contentPane);
        setTitle("Zakladanie noveho uctu klientovi");
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String cisloUctu = this.cisloUctuField.getText();
        Object typUctu = this.typUctuComboBox.getSelectedItem();
        try {
            double suma = Double.parseDouble(mnozstvoPenaziField.getText());
            this.ucet = new Ucet(cisloUctu, TypUctu.valueOf(typUctu + ""), this.klient);
            this.ucet.setMnozstvoPenazi(suma);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(hlavneOkno.getFrame(), "Zadana suma je neplatna","Error",JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(hlavneOkno.getFrame(), e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public Ucet getUcet() {
        return ucet;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    private void createUIComponents() {
        this.typUctuComboBox = new JComboBox<>(TypUctu.values());
    }
}
