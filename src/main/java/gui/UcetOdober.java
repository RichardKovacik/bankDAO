package gui;

import entity.Klient;
import entity.Ucet;
import org.example.Banka;

import javax.swing.*;
import java.awt.event.*;

public class UcetOdober extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> uctyKlientaBox;
    private HlavneOkno hlavneOkno;
    private Klient klient;
    private Banka banka;

    public UcetOdober(HlavneOkno hlavneOkno, Klient klient, Banka banka) {
        this.hlavneOkno = hlavneOkno;
        this.klient = klient;
        this.banka = banka;
        setTitle("Odobratie uctu klientovi");
        setContentPane(contentPane);
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
        int index = this.uctyKlientaBox.getSelectedIndex();
        if (index < 0){
            return;
        }
        this.banka.odoberUcetKlientovi(this.klient, index);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        if (this.klient.getUctyKlienta() == null){
            return;
        }
        String[] arr = this.klient.getUctyKlienta().stream()
                .map(String::valueOf)
                .toArray(String[]::new);

        this.uctyKlientaBox = new JComboBox<>(arr);
    }
}
