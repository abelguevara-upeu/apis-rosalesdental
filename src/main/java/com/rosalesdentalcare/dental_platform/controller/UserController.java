package com.rosalesdentalcare.dental_platform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.rosalesdentalcare.dental_platform.config.*;
import com.rosalesdentalcare.dental_platform.dto.UserDTO;
import com.rosalesdentalcare.dental_platform.service.impl.UserService;

public class UserController {
    @Autowired
     private UserService service;

     @GetMapping("/lista")
     public ResponseEntity<List<User>> list() {
          List<User> list = service.list();
          return new ResponseEntity<List<User>>(list, HttpStatus.OK);
     }

     @GetMapping("/detail/{id}")
     public ResponseEntity<?> getById(@PathVariable("id") Long id) {
          if (!service.existsById(id)) {
               return new ResponseEntity<Mensaje>(new Mensaje("No encontrado"), HttpStatus.NOT_FOUND);
          }
          User obj = service.getOne(id).get();
          return new ResponseEntity<User>(obj, HttpStatus.OK);
     }

     @PostMapping("/create")
     public ResponseEntity<Mensaje> create(@RequestBody UserDTO dto){
          if(Optional.ofNullable(dto).isEmpty())
               return  new ResponseEntity<Mensaje>(new Mensaje("Los campos son obligatorios"), HttpStatus.BAD_REQUEST);
          User User = new User().builder().idUser(dto.getIdUser()).username(dto.getUsername()).password(dto.getPassword()).build();
          service.save(User);
          return new ResponseEntity<Mensaje>(new Mensaje("Creación exitosa"), HttpStatus.OK);
     }

     @PutMapping("/update/{id}")
     public ResponseEntity<Mensaje> update(@PathVariable("id") Long id, @RequestBody UserDTO dto) {
          if (!service.existsById(id)){
               return new ResponseEntity<Mensaje>(new Mensaje("No encontrado"), HttpStatus.NOT_FOUND);
          }
          User obj = service.getOne(id).get();
          //TO-DO
          //obj.setDescCategoria(dto.getDescCategoria());
          service.save(obj);
          return new ResponseEntity<Mensaje>(new Mensaje("Actualización exitosa"), HttpStatus.OK);
     }

     @DeleteMapping("/delete/{id}")
     public ResponseEntity<Mensaje> delete(@PathVariable("id") Long id) {
          if (!service.existsById(id)) {
               return new ResponseEntity<Mensaje>(new Mensaje("No encontrado"), HttpStatus.NOT_FOUND);
          }
          service.delete(id);
          return new ResponseEntity<Mensaje>(new Mensaje("Eliminación exitosa"), HttpStatus.OK);
     }
}
