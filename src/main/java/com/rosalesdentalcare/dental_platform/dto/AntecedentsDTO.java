package com.rosalesdentalcare.dental_platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AntecedentsDTO {
    private Long idAntecedent;
    private Long personId;
    private String type;
    private String description;
    private Date date;
}
