package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.Appointment;
import java.util.List;
import java.util.Date;


public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    List<Appointment> findByAppointmentDate(Date appointmentDate);
    List<Appointment> findByState(String state);
}
