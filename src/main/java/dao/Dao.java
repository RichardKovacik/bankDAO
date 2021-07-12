package dao;

import java.util.List;

public interface Dao <T> {
    void pridaj(T t);
    void update(T t, String[] params);
    void vymaz(T t);
     T getElemet(int id);
    List<T> getZoznam();
}
