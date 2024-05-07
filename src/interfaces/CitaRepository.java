package interfaces;

import domain.Cita;

import java.util.List;

public interface CitaRepository {
    List<Cita> findAll();
    Cita findByid(int id);
    void save(Cita cita);
    void update(Cita cita);
    void delete(int id);

    void filterCitas(String cc);
}
