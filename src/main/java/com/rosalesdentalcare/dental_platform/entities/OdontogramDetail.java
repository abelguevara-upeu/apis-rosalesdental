package com.rosalesdentalcare.dental_platform.entities;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class OdontogramDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetail;

    @ManyToOne
    @JoinColumn(name = "idOdontogram", nullable = false)
    private Odontogram odontogram;

    private String tooth;
    private String toothCondition;
    private String treatment;
    private String observations;
}
