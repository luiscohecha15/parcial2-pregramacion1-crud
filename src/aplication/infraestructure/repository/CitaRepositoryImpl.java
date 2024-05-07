package aplication.infraestructure.repository;

import domain.Cita;
import interfaces.CitaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CitaRepositoryImpl implements CitaRepository {
    private static final String FILE_NAME = "citas.dat";
    @Override
    public List<Cita> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Cita>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Cita findByid(int id) {
        return findAll().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Cita cita) {
        List<Cita> citas = findAll();
        citas.add(cita);
        saveAll(citas);
    }

    @Override
    public void update(Cita cita) {
        List<Cita> citas = findAll();
        citas = citas.stream().map(p -> p.getId() == cita.getId() ? cita : p).collect(Collectors.toList());
        saveAll(citas);
    }



    @Override
    public void delete(int id) {


    }

    @Override
    public void filterCitas(String cc) {

        List<Cita> citas = findAll();

        for (Cita cita : citas) {
            if (cita.getPaciente().getCc().equals(cc)) {
                System.out.println(cita);
            }
        }
    }

    private void saveAll(List<Cita> citas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(citas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
