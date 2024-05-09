package interfaces;


import domain.Telefono;

import java.util.List;

public interface TelefonoService {
    List<Telefono> findAll();
    Telefono findByid(int imei);
    void save(Telefono telefono);
    void update(Telefono telefono);
    void delete(int imei);
}
