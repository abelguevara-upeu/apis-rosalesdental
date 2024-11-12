package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.dto.AppointmentDTO;
import com.rosalesdentalcare.dental_platform.entity.Appointment;
import com.rosalesdentalcare.dental_platform.entity.Patient;
import com.rosalesdentalcare.dental_platform.entity.Doctor;
import com.rosalesdentalcare.dental_platform.entity.Treatment;
import com.rosalesdentalcare.dental_platform.entity.AppointmentSchedule;
import com.rosalesdentalcare.dental_platform.service.impl.AppointmentService;
import com.rosalesdentalcare.dental_platform.service.impl.PatientService;
import com.rosalesdentalcare.dental_platform.service.impl.DoctorService;
import com.rosalesdentalcare.dental_platform.service.impl.TreatmentService;
import com.rosalesdentalcare.dental_platform.service.impl.AppointmentScheduleService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private AppointmentScheduleService scheduleService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Appointment>>> list() {
        List<Appointment> list = appointmentService.list();
        ApiResponse<List<Appointment>> response = new ApiResponse<>(true, "Lista de citas obtenida exitosamente", list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Appointment>> getById(@PathVariable("id") Long id) {
        if (!appointmentService.existsById(id)) {
            ApiResponse<Appointment> response = new ApiResponse<>(false, "Cita no encontrada", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Appointment obj = appointmentService.getOne(id).get();
        ApiResponse<Appointment> response = new ApiResponse<>(true, "Cita encontrada", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> create(@RequestBody AppointmentDTO dto) {
        if (dto == null) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Los campos son obligatorios", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = mapper.map(dto, Appointment.class);

        // Asignar el paciente
        if (dto.getPatientId() != null) {
            Optional<Patient> patientOpt = patientService.getOne(dto.getPatientId());
            patientOpt.ifPresent(appointment::setPatient);
        }

        // Asignar el doctor
        if (dto.getDoctorId() != null) {
            Optional<Doctor> doctorOpt = doctorService.getOne(dto.getDoctorId());
            doctorOpt.ifPresent(appointment::setDoctor);
        }

        // Asignar el tratamiento
        if (dto.getTreatmentId() != null) {
            Optional<Treatment> treatmentOpt = treatmentService.getOne(dto.getTreatmentId());
            treatmentOpt.ifPresent(appointment::setTreatment);
        }

        // Asignar el horario
        if (dto.getScheduleId() != null) {
            Optional<AppointmentSchedule> scheduleOpt = scheduleService.getOne(dto.getScheduleId());
            scheduleOpt.ifPresent(appointment::setSchedule);
        }

        appointmentService.save(appointment);
        ApiResponse<Object> response = new ApiResponse<>(true, "Cita creada exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Object>> update(@PathVariable("id") Long id, @RequestBody AppointmentDTO dto) {
        if (!appointmentService.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Cita no encontrada", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Appointment appointment = appointmentService.getOne(id).get();
        mapper.map(dto, appointment);  // Actualiza los campos del DTO en la entidad

        // Asignar el paciente
        if (dto.getPatientId() != null) {
            Optional<Patient> patientOpt = patientService.getOne(dto.getPatientId());
            patientOpt.ifPresent(appointment::setPatient);
        }

        // Asignar el doctor
        if (dto.getDoctorId() != null) {
            Optional<Doctor> doctorOpt = doctorService.getOne(dto.getDoctorId());
            doctorOpt.ifPresent(appointment::setDoctor);
        }

        // Asignar el tratamiento
        if (dto.getTreatmentId() != null) {
            Optional<Treatment> treatmentOpt = treatmentService.getOne(dto.getTreatmentId());
            treatmentOpt.ifPresent(appointment::setTreatment);
        }

        // Asignar el horario
        if (dto.getScheduleId() != null) {
            Optional<AppointmentSchedule> scheduleOpt = scheduleService.getOne(dto.getScheduleId());
            scheduleOpt.ifPresent(appointment::setSchedule);
        }

        appointment.setIdAppointment(id);  // Asegura que no se cambie el ID de la cita existente
        appointmentService.save(appointment);
        ApiResponse<Object> response = new ApiResponse<>(true, "Cita actualizada exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") Long id) {
        if (!appointmentService.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Cita no encontrada", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        appointmentService.delete(id);
        ApiResponse<Object> response = new ApiResponse<>(true, "Cita eliminada exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
