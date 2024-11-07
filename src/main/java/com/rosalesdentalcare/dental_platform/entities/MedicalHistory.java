package com.rosalesdentalcare.dental_platform.entities;

import jakarta.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class MedicalHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistory;

    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "idPatient", nullable = false)
    private Patient patient;

    private String allergies;
    private String observations;
}

