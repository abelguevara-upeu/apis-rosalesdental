package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.entity.Treatment;
import com.rosalesdentalcare.dental_platform.service.impl.TreatmentService;

@RestController
@RequestMapping("/api/treatments")
@CrossOrigin(origins = "http://localhost:4200")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Treatment>>> getAllTreatments() {
        List<Treatment> treatments = treatmentService.list();
        ApiResponse<List<Treatment>> response = new ApiResponse<>(true, "Lista de tratamientos obtenida exitosamente", treatments);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Treatment>> getTreatmentById(@PathVariable Long id) {
        Optional<Treatment> treatmentOpt = treatmentService.getOne(id);
        if (treatmentOpt.isPresent()) {
            ApiResponse<Treatment> response = new ApiResponse<>(true, "Tratamiento encontrado", treatmentOpt.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Treatment> response = new ApiResponse<>(false, "Tratamiento no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Treatment>> createTreatment(@RequestBody Treatment treatment) {
        treatmentService.save(treatment);
        ApiResponse<Treatment> response = new ApiResponse<>(true, "Tratamiento creado exitosamente", treatment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Treatment>> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatment) {
        if (!treatmentService.existsById(id)) {
            ApiResponse<Treatment> response = new ApiResponse<>(false, "Tratamiento no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        treatment.setIdTreatment(id);
        treatmentService.save(treatment);
        ApiResponse<Treatment> response = new ApiResponse<>(true, "Tratamiento actualizado exitosamente", treatment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTreatment(@PathVariable Long id) {
        if (!treatmentService.existsById(id)) {
            ApiResponse<Void> response = new ApiResponse<>(false, "Tratamiento no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        treatmentService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Tratamiento eliminado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
