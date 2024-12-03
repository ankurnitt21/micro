package com.example.UserService.service;

import com.example.UserService.dto.UserDTO;
import com.example.UserService.entity.UserS;
import com.example.UserService.helper.PasswordHelper;
import com.example.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordHelper passwordHelper;

    // Register user method now returns success/failure
    public String registerUser(UserDTO userDTO) {
        if(userRepository.existsByUsername(userDTO.getUsername())) {
            return "failure";  // Username already exists
        }
        String encodedPassword = passwordHelper.hashPassword(userDTO.getPassword());
        UserS user = new UserS();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "success";  // Successful registration
    }

    // Login user method now returns success/failure
    public String loginUser(UserDTO userDTO) {
        UserS user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null || !passwordHelper.matches(userDTO.getPassword(), user.getPassword())) {
            return "failure";  // Invalid username or password
        }
        return "success";  // Successful login
    }
}
