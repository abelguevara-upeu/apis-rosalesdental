package com.rosalesdentalcare.dental_platform.entity;

import jakarta.persistence.*;
import java.sql.Time;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class AppointmentSchedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSchedule;

    private Time startTime;
    private Time endTime;
}
