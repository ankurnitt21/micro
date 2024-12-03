package com.example.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data               // Lombok generates getters, setters, toString, equals, hashCode
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String password;
}
