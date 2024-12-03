package com.example.UserService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data               // Lombok generates getters, setters, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor // Lombok generates a constructor with all arguments
public class UserS { //Bythis name table will be created
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;
    private String password;

}
