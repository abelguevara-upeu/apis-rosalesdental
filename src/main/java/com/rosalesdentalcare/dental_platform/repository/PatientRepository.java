package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{}
