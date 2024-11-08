package com.rosalesdentalcare.dental_platform.entity;

import jakarta.persistence.*;
import java.math.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Treatment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTreatment;

    private String treatmentName;
    private String treatmentType;
    private String description;
    private BigDecimal cost;
}

