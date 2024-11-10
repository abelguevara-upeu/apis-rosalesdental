package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rosalesdentalcare.dental_platform.entity.Treatment;
import com.rosalesdentalcare.dental_platform.repository.TreatmentRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

@Service
public class TreatmentService implements CRUDService<Treatment, Long> {

    @Autowired
    private TreatmentRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Treatment> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Treatment> list() {
        return repository.findAll();
    }

    @Override
    public void save(Treatment obj) {
        repository.save(obj);
    }
}
