package org.example;

import entity.Klient;
import entity.Ucet;
import service.KlientService;
import service.UcetService;

public class Banka {
    private KlientService klientService;
    private UcetService ucetService;

    public Banka() {
        this.klientService = new KlientService();
        this.ucetService = new UcetService();
    }
    public void pridajKlienta(Klient klient) {
        this.klientService.uloz(klient);
    }
    public void odoberKlientaPodlaID(int id) {
        this.klientService.zmaz(id);
    }
    public void nacitajUctyKlienta(Klient klient){
       klient.nacitajUcty(this.ucetService.getZoznamVsetkych());
    }
    public void zalozUcetKlientovi(Klient klient, Ucet ucet) throws IllegalArgumentException {
        this.nacitajUctyKlienta(klient);
        if (!klient.pridajUcet(ucet)){
           throw new IllegalArgumentException("Dane cislo uctu sa uz nachadza v databaze");
        }
        this.ucetService.uloz(ucet);
    }
    public void odoberUcetKlientovi(Klient klient, int index) {
        Ucet u = klient.odoberUcet(index);
        if (u != null) {
            ucetService.zmaz(u.getId());
        }
    }

    public KlientService getKlientService() {
        return klientService;
    }

    public UcetService getUcetService() {
        return ucetService;
    }
}
