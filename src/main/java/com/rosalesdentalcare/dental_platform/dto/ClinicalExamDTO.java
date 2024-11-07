package com.rosalesdentalcare.dental_platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ClinicalExamDTO {
    private Long idExam;
    private Long medicalHistoryId;
    private String examType;
    private Date examDate;
    private String findings;
    private String recommendations;
}
