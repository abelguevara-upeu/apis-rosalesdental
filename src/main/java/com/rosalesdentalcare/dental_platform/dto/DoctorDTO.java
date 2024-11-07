package com.rosalesdentalcare.dental_platform.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long idDoctor;
    private Long personId;
    private String specialty;
}
