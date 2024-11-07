package com.rosalesdentalcare.dental_platform.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class VitalSigns {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVitalSign;

    @ManyToOne
    @JoinColumn(name = "idMedicalHistory", nullable = false)
    private MedicalHistory medicalHistory;

    private Date measurementDate;
    private String bloodPressure;
    private Integer heartRate;
    private BigDecimal temperature;
    private Integer respiratoryRate;
}

