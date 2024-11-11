package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.dto.MedicalHistoryDTO;
import com.rosalesdentalcare.dental_platform.entity.MedicalHistory;
import com.rosalesdentalcare.dental_platform.entity.Patient;
import com.rosalesdentalcare.dental_platform.service.impl.MedicalHistoryService;
import com.rosalesdentalcare.dental_platform.service.impl.PatientService;

@RestController
@RequestMapping("/api/medicalHistories")
public class MedicalHistoryController {

    @Autowired private MedicalHistoryService service;
    @Autowired private PatientService patientService;
    @Autowired private ModelMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<MedicalHistory>>> list () {
        List<MedicalHistory> list = service.list();
        ApiResponse<List<MedicalHistory>> response = new ApiResponse<>(true, "Lista de usuarios obtenida exitosamente", list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<MedicalHistory>> getById (@PathVariable("id") Long id) {
        if (!service.existsById(id)) {
            ApiResponse<MedicalHistory> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        MedicalHistory obj = service.getOne(id).get();
        ApiResponse<MedicalHistory> response = new ApiResponse<>(true, "Usuario encontrado", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> create(@RequestBody MedicalHistoryDTO dto) {
        if (dto == null) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Los campos son obligatorios", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Convertir el DTO a entidad usando ModelMapper (sin el paciente)
        MedicalHistory medicalHistory = mapper.map(dto, MedicalHistory.class);

        // Asignar el paciente usando el servicio, si `patientId` no es nulo
        if (dto.getPatientId() != null) {
            Patient patient = patientService.getOne(dto.getPatientId()).get();
            medicalHistory.setPatient(patient);
        }

        // Guardar el historial médico con el paciente asignado
        service.save(medicalHistory);
        ApiResponse<Object> response = new ApiResponse<>(true, "Usuario creado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Object>> update (@PathVariable("id") Long id, @RequestBody MedicalHistoryDTO dto) {
        if (!service.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        // Buscar el usuario existente
        MedicalHistory obj = service.getOne(id).get();

        // Mapear el DTO al usuario existente, actualizando solo los campos que están en el DTO
        mapper.map(dto, obj);

        // Asegurarnos de que el id del usuario no se sobrescriba (ya que es una entidad existente)
        obj.setIdHistory(id);

        // Guardar el usuario actualizado
        service.save(obj);

        ApiResponse<Object> response = new ApiResponse<>(true, "Usuario actualizado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> delete (@PathVariable("id") Long id) {
        if (!service.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        ApiResponse<Object> response = new ApiResponse<>(true, "Usuario eliminado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
