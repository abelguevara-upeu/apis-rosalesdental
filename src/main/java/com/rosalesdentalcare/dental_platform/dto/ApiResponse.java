package com.rosalesdentalcare.dental_platform.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
