package com.rosalesdentalcare.dental_platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DiagnosisDTO {
    private Long idDiagnosis;
    private Long medicalHistoryId;
    private Date diagnosisDate;
    private String description;
    private String recommendations;
}
