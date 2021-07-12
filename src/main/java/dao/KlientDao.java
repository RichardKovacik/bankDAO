package dao;
import entity.Klient;
import entity.Ucet;
import enums.Narodnost;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class KlientDao implements Dao<Klient> {
    private Session currentSession;
    private Transaction currentTransaction;

    public KlientDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Klient.class)
                .addAnnotatedClass(Ucet.class)
                .buildSessionFactory();;
        return factory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }
    @Override
    public void pridaj(Klient klient) {
        getCurrentSession().save(klient);
    }

    @Override
    public void update(Klient klient, String[] params) {
        klient.setMeno((params[0]));
        klient.setPriezvisko(params[1]);
        klient.setVek(Integer.parseInt(params[2]));
        klient.setNarodnost(Narodnost.valueOf(params[3]));
        getCurrentSession().update(klient);
    }

    @Override
    public void vymaz(Klient klient) {
        getCurrentSession().delete(klient);

    }

    @Override
    public Klient getElemet(int id) {
        return getCurrentSession().get(Klient.class, id);
    }

    @Override
    public List<Klient> getZoznam() {
        return getCurrentSession().createQuery("from Klient ").list();
    }
}
