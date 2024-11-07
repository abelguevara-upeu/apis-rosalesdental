package com.rosalesdentalcare.dental_platform.entities;

import jakarta.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class ClinicalExam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;

    @ManyToOne
    @JoinColumn(name = "idMedicalHistory", nullable = false)
    private MedicalHistory medicalHistory;

    private String examType;
    private Date examDate;
    private String findings;
    private String recommendations;
}

