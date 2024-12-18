    package com.rosalesdentalcare.dental_platform.controller;

    import java.util.List;
    import java.util.Optional;
    import java.time.LocalDateTime;
    import java.time.ZoneId;
    import java.time.ZonedDateTime;
    import java.util.Date;

    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.format.annotation.DateTimeFormat;
    import org.springframework.http.*;
    import org.springframework.web.bind.annotation.*;

    import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
    import com.rosalesdentalcare.dental_platform.dto.AppointmentDTO;
    import com.rosalesdentalcare.dental_platform.entity.Appointment;
    import com.rosalesdentalcare.dental_platform.entity.Patient;
    import com.rosalesdentalcare.dental_platform.entity.Doctor;
    import com.rosalesdentalcare.dental_platform.entity.Treatment;
    import com.rosalesdentalcare.dental_platform.service.impl.AppointmentService;
    import com.rosalesdentalcare.dental_platform.service.impl.PatientService;
    import com.rosalesdentalcare.dental_platform.service.impl.DoctorService;
    import com.rosalesdentalcare.dental_platform.service.impl.TreatmentService;

    @RestController
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/appointments")
    public class AppointmentController {

        @Autowired
        private AppointmentService service;

        @Autowired
        private PatientService patientService;

        @Autowired
        private DoctorService doctorService;

        @Autowired
        private TreatmentService treatmentService;


        @Autowired
        private ModelMapper mapper;

        @GetMapping("/getAll")
        public ResponseEntity<ApiResponse<List<Appointment>>> list() {
            List<Appointment> list = service.list();
            ApiResponse<List<Appointment>> response = new ApiResponse<>(true, "Lista de citas obtenida exitosamente", list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/getOne/{id}")
        public ResponseEntity<ApiResponse<Appointment>> getById(@PathVariable("id") Long id) {
            if (!service.existsById(id)) {
                ApiResponse<Appointment> response = new ApiResponse<>(false, "Cita no encontrada", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            Appointment obj = service.getOne(id).get();
            ApiResponse<Appointment> response = new ApiResponse<>(true, "Cita encontrada", obj);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @PostMapping("/create")
        public ResponseEntity<ApiResponse<Object>> create(@RequestBody AppointmentDTO dto) {
            if (dto == null) {
                ApiResponse<Object> response = new ApiResponse<>(false, "Los campos son obligatorios", null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Appointment obj = mapper.map(dto, Appointment.class);

            // Asignar el paciente
            if (dto.getPatientId() != null) {
                Optional<Patient> field = patientService.getOne(dto.getPatientId());
                field.ifPresent(obj::setPatient);
            }

            // Asignar el doctor
            if (dto.getDoctorId() != null) {
                Optional<Doctor> field = doctorService.getOne(dto.getDoctorId());
                field.ifPresent(obj::setDoctor);
            }

            // Asignar el tratamiento
            if (dto.getTreatmentId() != null) {
                Optional<Treatment> field = treatmentService.getOne(dto.getTreatmentId());
                field.ifPresent(obj::setTreatment);
            }

            service.save(obj);
            ApiResponse<Object> response = new ApiResponse<>(true, "Cita creada exitosamente", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<ApiResponse<Object>> update(@PathVariable("id") Long id, @RequestBody AppointmentDTO dto) {
            if (!service.existsById(id)) {
                ApiResponse<Object> response = new ApiResponse<>(false, "Cita no encontrada", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Appointment obj = service.getOne(id).get();

            obj.setAppointmentDate(dto.getAppointmentDate());
            obj.setAppointmentDateEnd(dto.getAppointmentDateEnd());
            obj.setNotes(dto.getNotes());
            obj.setState(dto.getState());

            if (dto.getPatientId() != null && (obj.getPatient() == null || !dto.getPatientId().equals(obj.getPatient().getIdPatient()))) {
                patientService.getOne(dto.getPatientId()).ifPresent(obj::setPatient);
            }

            if (dto.getDoctorId() != null && (obj.getDoctor() == null || !dto.getDoctorId().equals(obj.getDoctor().getIdDoctor()))) {
                doctorService.getOne(dto.getDoctorId()).ifPresent(obj::setDoctor);
            }

            if (dto.getTreatmentId() != null && (obj.getTreatment() == null || !dto.getTreatmentId().equals(obj.getTreatment().getIdTreatment()))) {
                treatmentService.getOne(dto.getTreatmentId()).ifPresent(obj::setTreatment);
            }

            service.save(obj);
            ApiResponse<Object> response = new ApiResponse<>(true, "Cita actualizada exitosamente", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


        @DeleteMapping("/delete/{id}")
        public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") Long id) {
            if (!service.existsById(id)) {
                ApiResponse<Object> response = new ApiResponse<>(false, "Cita no encontrada", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            service.delete(id);
            ApiResponse<Object> response = new ApiResponse<>(true, "Cita eliminada exitosamente", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        //

        @GetMapping("/filterState/{state}")
        public ResponseEntity<ApiResponse<List<Appointment>>> filterByState(@PathVariable("state") String state) {
            List<Appointment> list = service.filterByState(state);
            ApiResponse<List<Appointment>> response = new ApiResponse<>(true, "Lista de citas obtenida exitosamente", list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/filterDate/{date}")
            public ResponseEntity<ApiResponse<List<Appointment>>> filterByDate(
                @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dt) {

            // Convertir la fecha de entrada a UTC (si es necesario)
            ZoneId utcZone = ZoneId.of("UTC");
            Date date = Date.from(dt.atZone(utcZone).toInstant());

            List<Appointment> list = service.filterByDate(date);

            // Convertir las fechas de las citas de UTC a la zona horaria de Lima
            for (Appointment obj : list) {
                ZonedDateTime limaTime = ZonedDateTime.ofInstant(obj.getAppointmentDate().toInstant(), ZoneId.of("America/Lima"));
                obj.setAppointmentDate(Date.from(limaTime.toInstant()));
            }

            ApiResponse<List<Appointment>> response = new ApiResponse<>(true, "Lista de citas obtenida exitosamente", list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
