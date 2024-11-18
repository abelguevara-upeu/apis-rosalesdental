package com.rosalesdentalcare.dental_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
    List<Patient> findByPerson_Name(String name);
    List<Patient> findByPerson_Dni(String dni);
}
