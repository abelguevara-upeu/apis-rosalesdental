package com.rosalesdentalcare.dental_platform.entity;

import jakarta.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerson;
    private String firstName;
    private String lastNameFather;
    private String lastNameMother;
    private Character gender;
    private Date birthDate;
    private String phone;
    private String dni;
    private String address;
}
