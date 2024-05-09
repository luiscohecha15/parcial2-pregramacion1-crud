package aplication.service;


import domain.Telefono;
import interfaces.TelefonoRepository;
import interfaces.TelefonoService;

import java.util.List;

public class TelefonoServiceImpl implements TelefonoService {
    private final TelefonoRepository telefonoRepository;

    public TelefonoServiceImpl(TelefonoRepository telefonoRepository) {
        this.telefonoRepository = telefonoRepository;
    }

    @Override
    public List<Telefono> findAll() {
        return telefonoRepository.findAll();
    }

    @Override
    public Telefono findByid(int imei) {
        return telefonoRepository.findByid(imei);
    }

    @Override
    public void save(Telefono telefono) {
        telefonoRepository.save(telefono);
    }

    @Override
    public void update(Telefono telefono) {
        telefonoRepository.update(telefono);
    }

    @Override
    public void delete(int imei) {
        telefonoRepository.delete(imei);
    }
}
