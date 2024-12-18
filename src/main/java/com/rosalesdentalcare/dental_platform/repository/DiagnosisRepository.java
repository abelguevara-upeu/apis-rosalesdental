package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>{}
