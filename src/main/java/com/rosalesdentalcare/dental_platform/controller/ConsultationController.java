package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.dto.ConsultationDTO;
import com.rosalesdentalcare.dental_platform.entity.Consultation;
import com.rosalesdentalcare.dental_platform.entity.Person;
import com.rosalesdentalcare.dental_platform.service.impl.ConsultationService;
import com.rosalesdentalcare.dental_platform.service.impl.PersonService;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired private ConsultationService service;
    @Autowired private PersonService personService;
    @Autowired private ModelMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Consultation>>> list () {
        List<Consultation> list = service.list();
        ApiResponse<List<Consultation>> response = new ApiResponse<>(true, "Lista de usuarios obtenida exitosamente", list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Consultation>> getById (@PathVariable("id") Long id) {
        if (!service.existsById(id)) {
            ApiResponse<Consultation> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Consultation obj = service.getOne(id).get();
        ApiResponse<Consultation> response = new ApiResponse<>(true, "Usuario encontrado", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> create (@RequestBody ConsultationDTO dto) {
        if (Optional.ofNullable(dto).isEmpty()) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Los campos son obligatorios", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        // Convertir el DTO a entidad usando ModelMapper
        Consultation obj = mapper.map(dto, Consultation.class);

        if (dto.getPersonId()!= null) {
            Optional<Person> personOpt = personService.getOne(dto.getPersonId());
            if (personOpt.isPresent()){
                obj.setPerson(personOpt.get());
            } else {
                ApiResponse<Object> response = new ApiResponse<>(false, "Persona no encontrada", null);
            }
        }
        service.save(obj);
        ApiResponse<Object> response = new ApiResponse<>(true, "Usuario creado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Object>> update (@PathVariable("id") Long id, @RequestBody ConsultationDTO dto) {
        if (!service.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        // Buscar el usuario existente
        Consultation obj = service.getOne(id).get();

        // Mapear el DTO al usuario existente, actualizando solo los campos que están en el DTO
        mapper.map(dto, obj);

        if (dto.getPersonId()!= null) {
            Optional<Person> personOpt = personService.getOne(id);
            if (personOpt.isPresent()){
                obj.setPerson(personOpt.get());
            }
        }

        // Asegurarnos de que el id del usuario no se sobrescriba (ya que es una entidad existente)
        obj.setIdConsultation(id);

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
