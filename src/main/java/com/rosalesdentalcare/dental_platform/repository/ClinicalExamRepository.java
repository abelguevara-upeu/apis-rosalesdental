package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.ClinicalExam;

public interface ClinicalExamRepository extends JpaRepository<ClinicalExam, Long> {

}
