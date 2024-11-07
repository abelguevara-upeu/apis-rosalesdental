package com.rosalesdentalcare.dental_platform.entities;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;

    @ManyToOne
    @JoinColumn(name = "idPerson", nullable = false)
    private Person person;

    private String specialty;
}

