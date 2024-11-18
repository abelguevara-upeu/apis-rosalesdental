package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.dto.PatientDTO;
import com.rosalesdentalcare.dental_platform.entity.Person;
import com.rosalesdentalcare.dental_platform.entity.Patient;
import com.rosalesdentalcare.dental_platform.service.impl.PatientService;
import com.rosalesdentalcare.dental_platform.service.impl.PersonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired private PatientService patientService;
    @Autowired private PersonService personService;
    @Autowired private ModelMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Patient>>> list () {
        List<Patient> list = patientService.list();
        ApiResponse<List<Patient>> response = new ApiResponse<>(true, "Lista de usuarios obtenida exitosamente", list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Patient>> getById (@PathVariable("id") Long id) {
        if (!patientService.existsById(id)) {
            ApiResponse<Patient> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Patient obj = patientService.getOne(id).get();
        ApiResponse<Patient> response = new ApiResponse<>(true, "Usuario encontrado", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> create (@RequestBody PatientDTO dto) {
        if (Optional.ofNullable(dto).isEmpty()) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Los campos son obligatorios", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Patient obj = mapper.map(dto, Patient.class);

        if (dto.getPersonId() != null) {
            Optional<Person> field = personService.getOne(dto.getPersonId());
            field.ifPresent(obj::setPerson);
        }
        patientService.save(obj);
        ApiResponse<Object> response = new ApiResponse<>(true, "Paciente creado exitosamente", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Object>> update(@PathVariable("id") Long id, @RequestBody PatientDTO dto) {
        if (!patientService.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Paciente no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Patient obj = patientService.getOne(id).get();

        if (dto.getPersonId() != null && (obj.getPerson() == null || !dto.getPersonId().equals(obj.getPerson().getIdPerson()))) {
            personService.getOne(dto.getPersonId()).ifPresent(obj::setPerson);
        }

        patientService.save(obj);
        ApiResponse<Object> response = new ApiResponse<>(true, "Paciente actualizado exitosamente", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> delete (@PathVariable("id") Long id) {
        if (!patientService.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        patientService.delete(id);
        ApiResponse<Object> response = new ApiResponse<>(true, "Usuario eliminado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/filterByDate/{id}")
    public ResponseEntity<ApiResponse<Object>> filterByName (@PathVariable("id") String name) {
        patientService.filterByName(name);
        ApiResponse<Object> response = new ApiResponse<>(true, "Usuario eliminado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/filterByDni/{id}")
    public ResponseEntity<ApiResponse<Object>> filterByDni (@PathVariable("id") String dni) {
        patientService.filterByDni(dni);
        ApiResponse<Object> response = new ApiResponse<>(true, "Usuario eliminado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
