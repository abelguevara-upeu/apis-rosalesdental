package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {}
