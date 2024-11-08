package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rosalesdentalcare.dental_platform.entity.VitalSigns;
import com.rosalesdentalcare.dental_platform.repository.VitalSignsRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

public class VitalSignsService implements CRUDService<VitalSigns, Long> {

    @Autowired
    private VitalSignsRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<VitalSigns> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<VitalSigns> list() {
        return repository.findAll();
    }

    @Override
    public void save(VitalSigns obj) {
        repository.save(obj);
    }
}
