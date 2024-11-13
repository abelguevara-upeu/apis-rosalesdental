package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.entity.Doctor;
import com.rosalesdentalcare.dental_platform.service.impl.DoctorService;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
        List<Doctor> doctors = doctorService.list();
        ApiResponse<List<Doctor>> response = new ApiResponse<>(true, "Lista de doctores obtenida exitosamente", doctors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctorOpt = doctorService.getOne(id);
        if (doctorOpt.isPresent()) {
            ApiResponse<Doctor> response = new ApiResponse<>(true, "Doctor encontrado", doctorOpt.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Doctor> response = new ApiResponse<>(false, "Doctor no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Doctor>> createDoctor(@RequestBody Doctor doctor) {
        doctorService.save(doctor);
        ApiResponse<Doctor> response = new ApiResponse<>(true, "Doctor creado exitosamente", doctor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Doctor>> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        if (!doctorService.existsById(id)) {
            ApiResponse<Doctor> response = new ApiResponse<>(false, "Doctor no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        doctor.setIdDoctor(id);
        doctorService.save(doctor);
        ApiResponse<Doctor> response = new ApiResponse<>(true, "Doctor actualizado exitosamente", doctor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        if (!doctorService.existsById(id)) {
            ApiResponse<Void> response = new ApiResponse<>(false, "Doctor no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        doctorService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Doctor eliminado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
