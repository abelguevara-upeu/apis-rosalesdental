package com.rosalesdentalcare.dental_platform.entity;
import jakarta.persistence.*;
import java.util.Date;

import javax.print.DocFlavor.STRING;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAppointment;

    private Date appointmentDate;

    @ManyToOne
    @JoinColumn(name = "idPatient", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "idTreatment", nullable = false)
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "idSchedule", nullable = false)
    private AppointmentSchedule schedule;

    @ManyToOne
    @JoinColumn(name = "idDoctor", nullable = false)
    private Doctor doctor;

    private String notes;
    private String state;
}

