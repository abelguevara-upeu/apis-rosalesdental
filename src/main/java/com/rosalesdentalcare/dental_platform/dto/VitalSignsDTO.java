package com.rosalesdentalcare.dental_platform.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class VitalSignsDTO {
    private Long idVitalSign;
    private Long medicalHistoryId;
    private Date measurementDate;
    private String bloodPressure;
    private Integer heartRate;
    private BigDecimal temperature;
    private Integer respiratoryRate;
}
