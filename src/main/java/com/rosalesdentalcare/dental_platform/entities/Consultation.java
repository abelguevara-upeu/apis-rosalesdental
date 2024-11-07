package com.rosalesdentalcare.dental_platform.entities;

import jakarta.persistence.*;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultation;
    private Date consultationDate;

    @ManyToOne
    @JoinColumn(name = "idPerson", nullable = false)
    private Person person;

    private String reason;
    private String details;
}
