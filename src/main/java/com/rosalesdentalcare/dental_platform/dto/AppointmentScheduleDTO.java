package com.rosalesdentalcare.dental_platform.dto;

import java.sql.Time;

import lombok.Data;

@Data
public class AppointmentScheduleDTO {
    private Long idSchedule;
    private Time startTime;
    private Time endTime;
}
