package entity;

import enums.TypUctu;

import javax.persistence.*;

@Entity
public class Ucet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Cislo_Uctu")
    private String cisloUctu;

    @Column(name = "Typ_Uctu")
    @Enumerated(EnumType.STRING)
    private TypUctu typ;

    @Column(name = "Mnozstvo_Penazi")
    private double mnozstvoPenazi;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Klient_ID")
    private Klient klient;

    public Ucet(String cisloUctu, TypUctu typ, Klient klient) throws IllegalArgumentException {
        if (cisloUctu == null ||
                cisloUctu.isBlank() ||
                !cisloUctu.matches("[0-9]+")) {
            throw new IllegalArgumentException("Zadane cislo uctu je neplatne");
        }
        if (typ == null) {
            throw new IllegalArgumentException("Typ uctu nemoze byt prazdna");
        }

        this.cisloUctu = cisloUctu;
        this.typ = typ;
        this.klient = klient;
        this.mnozstvoPenazi = 0;
        this.klient.pridajUcet(this);
    }

    public Ucet() {

    }

    public String getCisloUctu() {
        return cisloUctu;
    }

    public void setCisloUctu(String cisloUctu) {
        this.cisloUctu = cisloUctu;
    }

    public TypUctu getTyp() {
        return typ;
    }

    public void setTyp(TypUctu typ) {
        this.typ = typ;
    }

    public double getMnozstvoPenazi() {
        return mnozstvoPenazi;
    }

    public void setMnozstvoPenazi(double mnozstvoPenazi) {
        this.mnozstvoPenazi = mnozstvoPenazi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ucet{" +
                "id=" + this.id +
                ", cisloUctu='" + this.cisloUctu + '\'' +
                ", typ=" + this.typ.getReprezentacia() +
                ", mnozstvoPenazi=" + this.mnozstvoPenazi +
                '}';
    }

    public Klient getKlient() {
        return klient;
    }
}
