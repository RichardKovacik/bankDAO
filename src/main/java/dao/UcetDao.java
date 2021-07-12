package dao;

import entity.Klient;
import entity.Ucet;
import enums.TypUctu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UcetDao implements Dao<Ucet> {
    private Session currentSession;
    private Transaction currentTransaction;

    public UcetDao() {
    }
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
    private static SessionFactory getSessionFactory() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Ucet.class)
                .addAnnotatedClass(Klient.class)
                .buildSessionFactory();;
        return factory;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    public Session getCurrentSession() {
        return currentSession;
    }
    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    public void pridaj(Ucet ucet) {
        getCurrentSession().save(ucet);
    }

    @Override
    public void update(Ucet ucet, String[] params) {
        ucet.setCisloUctu((params[0]));
        ucet.setTyp(TypUctu.valueOf(params[1]));
        ucet.setMnozstvoPenazi(Integer.parseInt(params[2]));
        getCurrentSession().update(ucet);
    }

    @Override
    public void vymaz(Ucet ucet) {
        getCurrentSession().delete(ucet);
    }

    @Override
    public Ucet getElemet(int id) {
        return getCurrentSession().get(Ucet.class, id);
    }

    @Override
    public List<Ucet> getZoznam() {
        return getCurrentSession().createQuery("from Ucet ").list();
    }
}
