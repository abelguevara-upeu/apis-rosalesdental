package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.dto.PersonDTO;
import com.rosalesdentalcare.dental_platform.entity.Person;
import com.rosalesdentalcare.dental_platform.service.impl.PersonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired private PersonService service;
    @Autowired private ModelMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Person>>> list () {
        List<Person> list = service.list();
        ApiResponse<List<Person>> response = new ApiResponse<>(true, "Lista de personas obtenida exitosamente", list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ApiResponse<Person>> getById (@PathVariable("id") Long id) {
        if (!service.existsById(id)) {
            ApiResponse<Person> response = new ApiResponse<>(false, "Persona no encontrada", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Person obj = service.getOne(id).get();
        ApiResponse<Person> response = new ApiResponse<>(true, "Persona encontrada", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> create (@RequestBody PersonDTO dto) {
        if (Optional.ofNullable(dto).isEmpty()) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Los campos son obligatorios", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Person obj = mapper.map(dto, Person.class);
        service.save(obj);
        ApiResponse<Object> response = new ApiResponse<>(true, "Persona creado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Object>> update (@PathVariable("id") Long id, @RequestBody PersonDTO dto) {
        if (!service.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Persona no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Person obj = service.getOne(id).get();
        mapper.map(dto, obj);
        obj.setIdPerson(id);
        service.save(obj);
        ApiResponse<Object> response = new ApiResponse<>(true, "Persona actualizada exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> delete (@PathVariable("id") Long id) {
        if (!service.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Persona no encontrada", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        ApiResponse<Object> response = new ApiResponse<>(true, "Persona eliminada exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
