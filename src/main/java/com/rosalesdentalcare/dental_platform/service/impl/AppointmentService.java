package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rosalesdentalcare.dental_platform.entity.Appointment;
import com.rosalesdentalcare.dental_platform.repository.AppointmentRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

@Service
public class AppointmentService implements CRUDService<Appointment, Long> {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Appointment> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Appointment> list() {
        return repository.findAll();
    }

    @Override
    public void save(Appointment obj) {
        repository.save(obj);
    }
}
