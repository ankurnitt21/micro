package com.example.UserService.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class SessionCheckFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String SESSION_KEY_PREFIX = "spring:session:sessions:";

    public SessionCheckFilter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        try {
            String pingResponse = redisTemplate.getConnectionFactory().getConnection().ping();
            System.out.println("Redis connection response: " + pingResponse);  // Should print "PONG"
        } catch (Exception e) {
            System.out.println("Error pinging Redis: " + e.getMessage());
            e.printStackTrace();
        }

        // Print all keys in Redis
        Set<String> allKeys = redisTemplate.keys("*");
        if (allKeys != null && !allKeys.isEmpty()) {
            System.out.println("All Redis Keys:");
            for (String key : allKeys) {
                System.out.println("Redis Key: " + key);
            }
        } else {
            System.out.println("No keys found in Redis.");
        }


        if (request.getRequestURI().equals("/login")) {
            if (allKeys != null && !allKeys.isEmpty()) {
                // Redirect to /products if a session exists
                response.sendRedirect("http://localhost:8081/api/orders/products");
                return;
            } else {
                filterChain.doFilter(request, response);
                return;
            }

        }
    }
}
