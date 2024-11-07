package com.rosalesdentalcare.dental_platform.dto;

import lombok.Data;

@Data
public class OdontogramDetailDTO {
    private Long idDetail;
    private Long odontogramId;
    private String tooth;
    private String toothCondition;
    private String treatment;
    private String observations;
}
