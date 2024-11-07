package com.rosalesdentalcare.dental_platform.config;

import jakarta.persistence.*;
import com.rosalesdentalcare.dental_platform.entities.Person;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @ManyToOne @JoinColumn(name = "idPerson", nullable = true)
    private Person person;

    private String username;
    private String password;
    private String role;
}
