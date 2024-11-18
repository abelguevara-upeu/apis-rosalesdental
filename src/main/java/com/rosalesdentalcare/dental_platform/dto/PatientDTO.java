package com.rosalesdentalcare.dental_platform.dto;

import lombok.Data;

@Data
public class PatientDTO {
    private Long idPatient;
    private Long personId;
    private String antecedents;
    private String alergies;
}
