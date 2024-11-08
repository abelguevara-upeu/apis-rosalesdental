package com.rosalesdentalcare.dental_platform.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatient;

    @ManyToOne
    @JoinColumn(name = "idPerson", nullable = false)
    private Person person;
}

