package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
