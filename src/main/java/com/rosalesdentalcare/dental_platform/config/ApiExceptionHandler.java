package com.rosalesdentalcare.dental_platform.config;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.rosalesdentalcare.dental_platform.dto.ApiResponse;
import com.rosalesdentalcare.dental_platform.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, "Error interno del servidor: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Otros métodos para manejar excepciones personalizadas
}
