package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rosalesdentalcare.dental_platform.entity.Person;
import com.rosalesdentalcare.dental_platform.repository.PersonRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

public class PersonService implements CRUDService<Person, Long> {

    @Autowired
    private PersonRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Person> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Person> list() {
        return repository.findAll();
    }

    @Override
    public void save(Person obj) {
        repository.save(obj);
    }
}
