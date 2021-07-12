package entity;

import enums.Narodnost;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String meno;

    @Column
    private String priezvisko;

    @Column
    private int vek;

    @Enumerated(EnumType.STRING)
    private Narodnost narodnost;

    @OneToMany(mappedBy = "klient",
               fetch = FetchType.EAGER,
               cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Ucet> uctyKlienta;

    public Klient(String meno, String priezvisko, int vek, Narodnost narodnost) throws IllegalArgumentException {
        String kontrolaVeku = vek+"";

        if (narodnost == null) {
            throw new IllegalArgumentException("Narodnost nemoze byt prazdna");
        }
        if (meno == null || meno.isBlank() || !meno.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Zadane meno je neplatne");
        }
        if (priezvisko == null || priezvisko.isBlank() || !priezvisko.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Zadane priezvisko je neplatne");
        }
        if (!kontrolaVeku.matches("[0-9]+") || vek < 1 || vek > 100) {
            throw new IllegalArgumentException("Zadany vek je neplatny");
        }

        this.meno = meno;
        this.priezvisko = priezvisko;
        this.vek = vek;
        this.narodnost = narodnost;
        this.uctyKlienta = new ArrayList<>();
    }

    public Klient() {
    }
    public void nacitajUcty(List<Ucet> uctyKlienta) {
        this.uctyKlienta = new ArrayList<>(uctyKlienta);
    }
    private boolean jeRovnakeCisloUctu(Ucet ucet) {
        Iterator<Ucet> iterator = this.uctyKlienta.iterator();

        while (iterator.hasNext()){
            Ucet u = iterator.next();
            if (u.getCisloUctu().equals(ucet.getCisloUctu())){
                return true;
            }
        }
        return false;

    }
    public boolean pridajUcet(Ucet ucet) {
        if (ucet != null && !this.jeRovnakeCisloUctu(ucet)) {
            this.uctyKlienta.add(ucet);
            return true;
        }
        return false;
    }
    public Ucet odoberUcet(int index) {
        if (index < 0 || index >= this.uctyKlienta.size()){
            return null;
        }
        return this.uctyKlienta.remove(index);
    }
    public int getPocetUctovKlienta(){
        return this.uctyKlienta.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

    public Narodnost getNarodnost() {
        return narodnost;
    }

    public void setNarodnost(Narodnost narodnost) {
        this.narodnost = narodnost;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", vek=" + vek +
                '}';
    }

    public List<Ucet> getUctyKlienta() {
        return uctyKlienta;
    }
}
