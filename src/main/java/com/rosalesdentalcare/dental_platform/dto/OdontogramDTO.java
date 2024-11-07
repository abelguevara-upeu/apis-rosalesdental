package com.rosalesdentalcare.dental_platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OdontogramDTO {
    private Long idOdontogram;
    private Long medicalHistoryId;
    private Date creationDate;
    private String details;
}
