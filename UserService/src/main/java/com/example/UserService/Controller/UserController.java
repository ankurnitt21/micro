package com.example.UserService.Controller;

import com.example.UserService.dto.UserDTO;
import com.example.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Show Login page (GET request)
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Thymeleaf template for login form
    }

    // Handle Login form submission (POST request)
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        UserDTO userDTO = new UserDTO(username, password); // Create DTO from form data
        String response = userService.loginUser(userDTO); // Call service for login validation

        // Based on the response from service, you can redirect or show a message
        if (response.equals("success")) {
            return "redirect:/";  // Redirect to home page if login is successful
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";  // Stay on login page and show error
        }
    }

    // Show Register page (GET request)
    @GetMapping("/register")
    public String registerPage() {
        return "register";  // Thymeleaf template for registration form
    }

    // Handle Register form submission (POST request)
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        UserDTO userDTO = new UserDTO(username, password); // Create DTO from form data
        String response = userService.registerUser(userDTO); // Call service for registration

        if (response.equals("success")) {
            return "redirect:/login";  // Redirect to login page after successful registration
        } else {
            model.addAttribute("error", "Registration failed: Username already exists");
            return "register";  // Stay on registration page and show error
        }
    }
}
