package com.rosalesdentalcare.dental_platform.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AppointmentDTO {
    private Long idAppointment;
    private Date appointmentDate;
    private Long patientId;
    private Long treatmentId;
    private Long scheduleId;
    private Long doctorId;
    private String notes;
}
