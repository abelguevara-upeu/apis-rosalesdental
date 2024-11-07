package com.rosalesdentalcare.dental_platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MedicalHistoryDTO {
    private Long idHistory;
    private Date creationDate;
    private Long patientId;
    private String allergies;
    private String observations;
}
