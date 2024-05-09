package interfaces;

import domain.Telefono;

import java.util.List;

public interface TelefonoRepository {
    List<Telefono> findAll();
    Telefono findByid(int id);
    void save(Telefono telefono);
    void update(Telefono telefono);
    void delete(int imei);

}
