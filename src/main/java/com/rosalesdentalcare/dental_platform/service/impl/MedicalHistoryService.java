package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rosalesdentalcare.dental_platform.entity.MedicalHistory;
import com.rosalesdentalcare.dental_platform.repository.MedicalHistoryRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

public class MedicalHistoryService implements CRUDService<MedicalHistory, Long> {

    @Autowired
    private MedicalHistoryRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<MedicalHistory> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MedicalHistory> list() {
        return repository.findAll();
    }

    @Override
    public void save(MedicalHistory obj) {
        repository.save(obj);
    }
}
