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
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setZipCode(userDTO.getZipCode());

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

    public UserDTO getUserDetails(String username) {
        UserS user = userRepository.findByUsername(username);
        if (user != null) {
            return new UserDTO(
                    user.getUsername(),
                    user.getPassword(), // You might not need to return the password
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoneNumber(),
                    user.getAddress(),
                    user.getCity(),
                    user.getCountry(),
                    user.getZipCode()
            );
        }
        return null;  // If user not found
    }
}
