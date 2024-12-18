package com.rosalesdentalcare.dental_platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PersonDTO {
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
