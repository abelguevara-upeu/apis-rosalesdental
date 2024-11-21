package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rosalesdentalcare.dental_platform.entity.User;
import com.rosalesdentalcare.dental_platform.repository.UserRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

@Service @Transactional
public class UserService implements CRUDService<User, Long> {

    @Autowired
    private UserRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<User> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> list() {
        return repository.findAll();
    }

    @Override
    public void save(User obj) {
        repository.save(obj);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
