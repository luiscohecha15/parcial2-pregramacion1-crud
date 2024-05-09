package aplication.infraestructure.repository;


import domain.Telefono;
import interfaces.TelefonoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TelefonoRepositoryImpl implements TelefonoRepository {
    private static final String FILE_NAME = "telefonos.dat";

    public TelefonoRepositoryImpl() {
    }

    @Override
    public List<Telefono> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Telefono>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Telefono findByid(int imei) {
        return findAll().stream().filter(p -> p.getImei() == imei).findFirst().orElse(null);
    }

    @Override
    public void save(Telefono telefono) {
        List<Telefono> telefonos = findAll();
        telefonos.add(telefono);
        saveAll(telefonos);
    }

    @Override
    public void update(Telefono telefono) {
        List<Telefono> telefonos = findAll();
        telefonos = telefonos.stream().map(p -> p.getImei() == telefono.getImei() ? telefono : p).collect(Collectors.toList());
        saveAll(telefonos);
    }



    @Override
    public void delete(int imei) {
        List<Telefono> telefonos = findAll();
        telefonos = telefonos.stream().filter(x -> x.getImei() != imei).collect(Collectors.toList());

    }

    private void saveAll(List<Telefono> telefonos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(telefonos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
