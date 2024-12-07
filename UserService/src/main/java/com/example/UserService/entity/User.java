package com.example.UserService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data               // Lombok generates getters, setters, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor // Lombok generates a constructor with all arguments
@Table(name = "userdetails")
public class User { // The name of the table will be `userdetails`
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

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
