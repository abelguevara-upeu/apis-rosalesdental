package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rosalesdentalcare.dental_platform.entity.Diagnosis;
import com.rosalesdentalcare.dental_platform.repository.DiagnosisRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

public class DiagnosisService implements CRUDService<Diagnosis, Long> {

    @Autowired
    private DiagnosisRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Diagnosis> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Diagnosis> list() {
        return repository.findAll();
    }

    @Override
    public void save(Diagnosis obj) {
        repository.save(obj);
    }
}
