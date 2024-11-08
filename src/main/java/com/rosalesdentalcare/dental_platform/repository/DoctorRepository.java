package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
