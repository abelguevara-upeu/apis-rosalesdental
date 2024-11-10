package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rosalesdentalcare.dental_platform.entity.OdontogramDetail;
import com.rosalesdentalcare.dental_platform.repository.OdontogramDetailRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

@Service
public class OdontogramDetailService implements CRUDService<OdontogramDetail, Long> {

    @Autowired
    private OdontogramDetailRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<OdontogramDetail> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<OdontogramDetail> list() {
        return repository.findAll();
    }

    @Override
    public void save(OdontogramDetail obj) {
        repository.save(obj);
    }
}
