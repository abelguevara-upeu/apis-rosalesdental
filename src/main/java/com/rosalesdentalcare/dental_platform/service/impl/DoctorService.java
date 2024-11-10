package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rosalesdentalcare.dental_platform.entity.Doctor;
import com.rosalesdentalcare.dental_platform.repository.DoctorRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

@Service
public class DoctorService implements CRUDService<Doctor, Long> {

    @Autowired
    private DoctorRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Doctor> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Doctor> list() {
        return repository.findAll();
    }

    @Override
    public void save(Doctor obj) {
        repository.save(obj);
    }
}
