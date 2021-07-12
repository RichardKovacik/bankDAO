package service;

import dao.UcetDao;
import entity.Ucet;

import java.util.List;

public class UcetService {
    private UcetDao ucetDao;

    public UcetService() {
        this.ucetDao = new UcetDao();
    }
    public void uloz(Ucet ucet) {
        ucetDao.openCurrentSessionwithTransaction();
        ucetDao.pridaj(ucet);
        ucetDao.closeCurrentSessionwithTransaction();
    }
    public void zmaz(int id) {
        ucetDao.openCurrentSessionwithTransaction();
        Ucet ucet = ucetDao.getElemet(id);
        ucetDao.vymaz(ucet);
        ucetDao.closeCurrentSessionwithTransaction();
    }
    public void zmen(Ucet ucet, String[] params){
        ucetDao.openCurrentSessionwithTransaction();
        ucetDao.update(ucet, params);
        ucetDao.closeCurrentSessionwithTransaction();
    }
    public Ucet getUcetPodlaId(int id) {
        ucetDao.openCurrentSession();
        Ucet ucet = ucetDao.getElemet(id);
        ucetDao.closeCurrentSession();
        return ucet;
    }
    public List<Ucet> getZoznamVsetkych(){
        ucetDao.openCurrentSession();
        List<Ucet> vsetkyUcty = ucetDao.getZoznam();
        ucetDao.closeCurrentSession();
        return vsetkyUcty;
    }
}
