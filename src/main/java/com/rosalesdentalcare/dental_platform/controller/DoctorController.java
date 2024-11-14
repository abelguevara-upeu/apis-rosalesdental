package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.dto.DoctorDTO;
import com.rosalesdentalcare.dental_platform.entity.Doctor;
import com.rosalesdentalcare.dental_platform.entity.Person;
import com.rosalesdentalcare.dental_platform.service.impl.DoctorService;
import com.rosalesdentalcare.dental_platform.service.impl.PersonService;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @Autowired
    private PersonService personService;

    @Autowired
    private  ModelMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
        List<Doctor> doctors = service.list();
        ApiResponse<List<Doctor>> response = new ApiResponse<>(true, "Lista de doctores obtenida exitosamente", doctors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctorOpt = service.getOne(id);
        if (doctorOpt.isPresent()) {
            ApiResponse<Doctor> response = new ApiResponse<>(true, "Doctor encontrado", doctorOpt.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Doctor> response = new ApiResponse<>(false, "Doctor no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> create(@RequestBody DoctorDTO dto) {
        if (dto == null) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Los campos son obligatorios", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Doctor obj = mapper.map(dto, Doctor.class);

        if (dto.getPersonId() != null) {
            Optional<Person> field = personService.getOne(dto.getPersonId());
            field.ifPresent(obj::setPerson);
        }

        service.save(obj);
        ApiResponse<Object> response = new ApiResponse<>(true, "Cita creada exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Doctor>> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO dto) {
        if (!service.existsById(id)) {
            ApiResponse<Doctor> response = new ApiResponse<>(false, "Doctor no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Doctor obj = service.getOne(id).get();

        obj.setSpecialty(dto.getSpecialty());

        if (dto.getPersonId() != null && (obj.getPerson() == null || !dto.getPersonId().equals(obj.getPerson().getIdPerson()))) {
            personService.getOne(dto.getPersonId()).ifPresent(obj::setPerson);
        }

        obj.setIdDoctor(id);
        service.save(obj);
        ApiResponse<Doctor> response = new ApiResponse<>(true, "Doctor actualizado exitosamente", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        if (!service.existsById(id)) {
            ApiResponse<Void> response = new ApiResponse<>(false, "Doctor no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Doctor eliminado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
