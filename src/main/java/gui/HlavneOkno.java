package gui;

import entity.Klient;
import model.KlientModel;
import model.UcetModel;
import org.example.Banka;
import javax.swing.*;

public class HlavneOkno {
    private JPanel panel1;
    private JButton pridajButton;
    private JButton editujButton;
    private JButton vymazButton;
    private JTable table1;
    private JButton klientiButton;
    private JButton uctyButton;
    private JButton zalozUcetButton;
    private JButton odoberUcetButton;
    private JFrame frame;
    private Banka banka;

    public HlavneOkno() {
        this.banka = new Banka();
        this.frame = new JFrame("Banka");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.add(this.panel1);
        this.frame.pack();
        this.frame.setVisible(true);

        pridajButton.addActionListener(e -> HlavneOkno.this.pridajKlienta());
        vymazButton.addActionListener(e -> HlavneOkno.this.vymazKlienta());
        editujButton.addActionListener(e -> HlavneOkno.this.zmenUdajeOKlientovi());
        uctyButton.addActionListener(e -> HlavneOkno.this.zobrazUcty());
        klientiButton.addActionListener(e -> HlavneOkno.this.zobrazKlientov());
        zalozUcetButton.addActionListener(e -> HlavneOkno.this.zalozUcetKlientovi());
        odoberUcetButton.addActionListener(e -> HlavneOkno.this.odoberUcet());
    }

    private void odoberUcet() {
        int index = this.table1.getSelectedRow();
        if (index < 0){
            return;
        }
        int id = this.banka.getKlientService().getZoznamVsetkych().get(index).getId();
        Klient klient = this.banka.getKlientService().getKlientPodlaId(id);

        UcetOdober ucetOdober = new UcetOdober(this, klient, this.banka);
        ucetOdober.setLocationRelativeTo(this.frame);
        ucetOdober.pack();
        ucetOdober.setVisible(true);

    }

    private void zalozUcetKlientovi() {
        int index = this.table1.getSelectedRow();
        if (index < 0){
            return;
        }
        int id = this.banka.getKlientService().getZoznamVsetkych().get(index).getId();
        Klient klient = this.banka.getKlientService().getKlientPodlaId(id);

        UcetEdit ucetEdit = new UcetEdit(this);
        ucetEdit.setKlient(klient);
        ucetEdit.setLocationRelativeTo(this.frame);
        ucetEdit.pack();
        ucetEdit.setVisible(true);

        if (ucetEdit.getUcet() != null) {
            try {
                this.banka.zalozUcetKlientovi(klient, ucetEdit.getUcet());
            } catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(this.frame, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void zobrazKlientov() {
        this.nacitajKlientov();
    }

    private void zobrazUcty() {
        UcetModel ucetModel = new UcetModel(this.banka.getUcetService().getZoznamVsetkych());
        this.table1.setModel(ucetModel);
        this.editujButton.setEnabled(false);
        this.pridajButton.setEnabled(false);
        this.vymazButton.setEnabled(false);
        this.zalozUcetButton.setEnabled(false);
        this.odoberUcetButton.setEnabled(false);
    }

    private void zmenUdajeOKlientovi() {
        int index = this.table1.getSelectedRow();
        if (index < 0){
            return;
        }

        int id = this.banka.getKlientService().getZoznamVsetkych().get(index).getId();
        Klient klient = this.banka.getKlientService().getKlientPodlaId(id);
        KlientEdit edit = new KlientEdit(klient, this);

        edit.setLocationRelativeTo(this.frame);
        edit.pack();
        edit.setVisible(true);
        String[] zmeneneUdaje = new String[4];

        zmeneneUdaje[0] = edit.getKlient().getMeno();
        zmeneneUdaje[1] = edit.getKlient().getPriezvisko();
        zmeneneUdaje[2] = edit.getKlient().getVek()+"";
        zmeneneUdaje[3] = edit.getKlient().getNarodnost().getReprezentacia();

        this.banka.getKlientService().zmen(klient, zmeneneUdaje);
        this.nacitajKlientov();
    }

    private void vymazKlienta() {
        int indexOds = this.table1.getSelectedRow();
        if (indexOds < 0){
            return;
        }
        int odpoved = JOptionPane.showConfirmDialog(this.frame, "Naozaj chces vymazat oznaceneho klienta ?", "Potvrd",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (odpoved == JOptionPane.NO_OPTION){
            return;
        }

        int Id = this.banka.getKlientService().getZoznamVsetkych().get(indexOds).getId();
        banka.odoberKlientaPodlaID(Id);
        this.nacitajKlientov();
    }

    private void pridajKlienta() {
        KlientEdit edit = new KlientEdit(this);
        edit.setLocationRelativeTo(this.frame);
        edit.pack();
        edit.setVisible(true);

        if (edit.getKlient() != null) {
            this.banka.pridajKlienta(edit.getKlient());
        }
        this.nacitajKlientov();
    }

    private void nacitajKlientov() {
        KlientModel klientModel = new KlientModel(this.banka.getKlientService().getZoznamVsetkych());
        this.table1.setModel(klientModel);
        this.editujButton.setEnabled(true);
        this.pridajButton.setEnabled(true);
        this.vymazButton.setEnabled(true);
        this.zalozUcetButton.setEnabled(true);
        this.odoberUcetButton.setEnabled(true);

    }

    private void createUIComponents() {
        this.table1 = new JTable();
    }

    public JFrame getFrame() {
        return frame;
    }
}
