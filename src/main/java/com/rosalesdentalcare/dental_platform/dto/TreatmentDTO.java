package com.rosalesdentalcare.dental_platform.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TreatmentDTO {
    private Long idTreatment;
    private String treatmentName;
    private String treatmentType;
    private String description;
    private BigDecimal cost;
}
