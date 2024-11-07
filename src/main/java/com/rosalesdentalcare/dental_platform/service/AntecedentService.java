package com.rosalesdentalcare.dental_platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rosalesdentalcare.dental_platform.entities.Antecedent;
import com.rosalesdentalcare.dental_platform.repository.AntecedentRepository;

public class AntecedentService implements CRUDService<Antecedent, Long> {

    @Autowired
    private AntecedentRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Antecedent> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Antecedent> list() {
        return repository.findAll();
    }

    @Override
    public void save(Antecedent obj) {
        repository.save(obj);
    }
}
