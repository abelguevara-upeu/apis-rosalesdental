package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.dto.UserDTO;
import com.rosalesdentalcare.dental_platform.entity.User;
import com.rosalesdentalcare.dental_platform.service.impl.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UserService service;
    @Autowired private ModelMapper mapper;

    @GetMapping("/lista")
    public ResponseEntity<ApiResponse<List<User>>> list () {
        List<User> list = service.list();
        ApiResponse<List<User>> response = new ApiResponse<>(true, "Lista de usuarios obtenida exitosamente", list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ApiResponse<User>> getById (@PathVariable("id") Long id) {
        if (!service.existsById(id)) {
            ApiResponse<User> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        User obj = service.getOne(id).get();
        ApiResponse<User> response = new ApiResponse<>(true, "Usuario encontrado", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> create (@RequestBody UserDTO dto) {
        if (Optional.ofNullable(dto).isEmpty()) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Los campos son obligatorios", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        // Convertir el DTO a entidad usando ModelMapper
        User obj = mapper.map(dto, User.class);
        service.save(obj);
        ApiResponse<Object> response = new ApiResponse<>(true, "Usuario creado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Object>> update (@PathVariable("id") Long id, @RequestBody UserDTO dto) {
        if (!service.existsById(id)) {
            ApiResponse<Object> response = new ApiResponse<>(false, "Usuario no encontrado", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        // Buscar el usuario existente
        User obj = service.getOne(id).get();

        // Mapear el DTO al usuario existente, actualizando solo los campos que están en el DTO
        mapper.map(dto, obj);

        // Asegurarnos de que el id del usuario no se sobrescriba (ya que es una entidad existente)
        obj.setIdUser(id);

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
