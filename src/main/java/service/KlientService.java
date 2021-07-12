package service;

import dao.KlientDao;
import entity.Klient;

import java.util.List;

public class KlientService {
    private KlientDao klientDao;

    public KlientService() {
        this.klientDao = new KlientDao();
    }
    public void uloz(Klient klient) {
        klientDao.openCurrentSessionwithTransaction();
        klientDao.pridaj(klient);
        klientDao.closeCurrentSessionwithTransaction();
    }
    public void zmaz(int id) {
        klientDao.openCurrentSessionwithTransaction();
        Klient klient = klientDao.getElemet(id);
        klientDao.vymaz(klient);
        klientDao.closeCurrentSessionwithTransaction();
    }
    public void zmen(Klient klient, String[] params){
        klientDao.openCurrentSessionwithTransaction();
        klientDao.update(klient, params);
        klientDao.closeCurrentSessionwithTransaction();
    }
    public Klient getKlientPodlaId(int id) {
        klientDao.openCurrentSession();
        Klient klient = klientDao.getElemet(id);
        klientDao.closeCurrentSession();
        return klient;
    }
    public List<Klient> getZoznamVsetkych(){
        klientDao.openCurrentSession();
        List<Klient> vsetciKlienti = klientDao.getZoznam();
        klientDao.closeCurrentSession();
        return vsetciKlienti;
    }
}
