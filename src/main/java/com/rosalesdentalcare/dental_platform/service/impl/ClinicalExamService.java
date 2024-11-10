package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rosalesdentalcare.dental_platform.entity.ClinicalExam;
import com.rosalesdentalcare.dental_platform.repository.ClinicalExamRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

@Service
public class ClinicalExamService implements CRUDService<ClinicalExam, Long> {

    @Autowired
    private ClinicalExamRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<ClinicalExam> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ClinicalExam> list() {
        return repository.findAll();
    }

    @Override
    public void save(ClinicalExam obj) {
        repository.save(obj);
    }
}
