package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rosalesdentalcare.dental_platform.entity.Consultation;
import com.rosalesdentalcare.dental_platform.repository.ConsultationRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

@Service
public class ConsultationService implements CRUDService<Consultation, Long> {

    @Autowired
    private ConsultationRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Consultation> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Consultation> list() {
        return repository.findAll();
    }

    @Override
    public void save(Consultation obj) {
        repository.save(obj);
    }
}
