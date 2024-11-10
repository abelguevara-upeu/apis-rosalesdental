package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rosalesdentalcare.dental_platform.entity.Odontogram;
import com.rosalesdentalcare.dental_platform.repository.OdontogramRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

@Service
public class OdontogramService implements CRUDService<Odontogram, Long> {

    @Autowired
    private OdontogramRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Odontogram> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Odontogram> list() {
        return repository.findAll();
    }

    @Override
    public void save(Odontogram obj) {
        repository.save(obj);
    }
}
