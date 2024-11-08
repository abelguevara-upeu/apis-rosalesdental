package com.rosalesdentalcare.dental_platform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rosalesdentalcare.dental_platform.entity.AppointmentSchedule;
import com.rosalesdentalcare.dental_platform.repository.AppointmentScheduleRepository;
import com.rosalesdentalcare.dental_platform.service.CRUDService;

public class AppointmentScheduleService implements CRUDService<AppointmentSchedule, Long> {

    @Autowired
    private AppointmentScheduleRepository repository;

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<AppointmentSchedule> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<AppointmentSchedule> list() {
        return repository.findAll();
    }

    @Override
    public void save(AppointmentSchedule obj) {
        repository.save(obj);
    }
}
