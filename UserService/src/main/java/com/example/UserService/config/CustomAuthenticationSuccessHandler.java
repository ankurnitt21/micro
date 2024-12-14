package com.example.UserService.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Check if a session already exists (if user is already authenticated)
        if (authentication != null && authentication.isAuthenticated()) {
            // If authenticated, redirect to /products
            response.sendRedirect("http://localhost:8081/api/orders/products");
        } else {
            // If not authenticated, continue with the default login process (you could redirect to /home or wherever you prefer)
            response.sendRedirect("/login");
        }
    }
}

