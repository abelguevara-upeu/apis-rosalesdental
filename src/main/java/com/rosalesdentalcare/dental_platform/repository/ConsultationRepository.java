package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long>{}
