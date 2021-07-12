package gui;

import entity.Klient;
import enums.Narodnost;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class KlientEdit extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField menoField;
    private JTextField priezviskoField;
    private JTextField vekField;
    private JComboBox<Narodnost> narodnostBox;
    private Klient klient;
    private HlavneOkno hlavneOkno;

    public KlientEdit(HlavneOkno hlavneOkno) {
        this.hlavneOkno = hlavneOkno;
        setContentPane(contentPane);
        setTitle("Pridavanie noveho klienta");
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    public KlientEdit(Klient klient, HlavneOkno hlavneOkno) {
        this(hlavneOkno);
        setTitle("Edit klienta");
        this.klient = klient;
        this.menoField.setText(klient.getMeno());
        this.priezviskoField.setText(klient.getPriezvisko());
        this.vekField.setText(klient.getVek()+"");
    }

    private void onOK() {
        String meno = this.menoField.getText();
        String priezvisko = this.priezviskoField.getText();
        Object narodnost =  this.narodnostBox.getSelectedItem();

        try {
            int vek = Integer.parseInt(this.vekField.getText());
            this.klient = new Klient(meno, priezvisko, vek , Narodnost.valueOf(narodnost+""));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(hlavneOkno.getFrame(), "Zadany vek je neplatny","Error",JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(hlavneOkno.getFrame(), e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public Klient getKlient() {
        return klient;
    }

    private void createUIComponents() {
        Map<Object, Icon> icons = new HashMap<>();
        icons.put(Narodnost.CZ, new ImageIcon("src/main/java/flags/cz_flag.png"));
        icons.put(Narodnost.PL, new ImageIcon("src/main/java/flags/pl_flag.png"));
        icons.put(Narodnost.USA, new ImageIcon("src/main/java/flags/usa_flag.png"));
        icons.put(Narodnost.DE, new ImageIcon("src/main/java/flags/nem_flag.png"));
        this.narodnostBox = new JComboBox<>(Narodnost.values());
        this.narodnostBox.setRenderer(new IconListRenderer(icons));
    }
}
