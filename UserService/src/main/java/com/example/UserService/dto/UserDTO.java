package com.example.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data               // Lombok generates getters, setters, toString, equals, hashCode
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String zipCode;
}
