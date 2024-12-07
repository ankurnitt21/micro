package com.example.UserService.Controller;

import com.example.UserService.dto.UserDTO;
import com.example.UserService.exception.UnauthorizedAccessException;
import com.example.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    // Show Login page (GET request)
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Thymeleaf template for login form
    }

    // Show Register page (GET request)
    @GetMapping("/register")
    public String registerPage() {
        return "register";  // Thymeleaf template for registration form
    }

    // Handle Register form submission (POST request)
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password,
                               @RequestParam String email, @RequestParam String firstName,
                               @RequestParam String lastName, @RequestParam String phoneNumber,
                               @RequestParam String address, @RequestParam String city,
                               @RequestParam String country, @RequestParam String zipCode,
                               Model model) {
        UserDTO userDTO = new UserDTO(username, password, email, firstName, lastName, phoneNumber,
                address, city, country, zipCode); // Create DTO from form data
        String response = userService.registerUser(userDTO); // Call service for registration

        if (response.equals("success")) {
            return "redirect:/login";  // Redirect to login page after successful registration
        } else {
            model.addAttribute("error", "Registration failed: Username already exists");
            return "register";  // Stay on registration page and show error
        }
    }

    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getuserdetail/{username}")
    public UserDTO userdetail(@PathVariable String username, Model model) {

        UserDTO userdetail = userService.getUserDetails(username);
        return userdetail;
    }

}
