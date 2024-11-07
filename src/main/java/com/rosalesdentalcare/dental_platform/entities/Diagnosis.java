package com.rosalesdentalcare.dental_platform.entities;

import jakarta.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Diagnosis {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiagnosis;

    @ManyToOne
    @JoinColumn(name = "idMedicalHistory", nullable = false)
    private MedicalHistory medicalHistory;

    private Date diagnosisDate;
    private String description;
    private String recommendations;
}

