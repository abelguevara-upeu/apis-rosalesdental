package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rosalesdentalcare.dental_platform.entity.Patient;
import com.rosalesdentalcare.dental_platform.repository.PatientRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

public class PatientService implements CRUDService<Patient, Long> {

    @Autowired
    private PatientRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Patient> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Patient> list() {
        return repository.findAll();
    }

    @Override
    public void save(Patient obj) {
        repository.save(obj);
    }
}
