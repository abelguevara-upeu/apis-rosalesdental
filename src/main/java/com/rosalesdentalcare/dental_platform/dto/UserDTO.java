package com.rosalesdentalcare.dental_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserDTO {
    private Long idUser;
    private Long personId;
    private String username;
    private String password;
    private String role;
}
