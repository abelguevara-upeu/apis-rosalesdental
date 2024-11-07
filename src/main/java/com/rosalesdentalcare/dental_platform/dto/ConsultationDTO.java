package com.rosalesdentalcare.dental_platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ConsultationDTO {
    private Long idConsultation;
    private Date consultationDate;
    private Long personId;
    private String reason;
    private String details;
}
