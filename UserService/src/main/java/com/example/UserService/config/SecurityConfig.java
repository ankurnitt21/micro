package com.example.UserService.config;

import com.example.UserService.repository.UserRepository;
import com.example.UserService.repository.UserRoleRepository;
import com.example.UserService.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // PreAuthorize, Secured etc..
@EnableRedisHttpSession
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/register").permitAll()  // Allow access to /register without authentication
                                .anyRequest().authenticated()             // Require authentication for all other requests
                )
                .formLogin(login -> login.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")  // Logout URL
                        .logoutSuccessUrl("/login")  // After logout, redirect to login
                        .invalidateHttpSession(true)  // Invalidate the session on logout
                        .clearAuthentication(true)  // Clear authentication on logout
                        .deleteCookies("JSESSIONID")  // Delete the session cookie
                        .addLogoutHandler((request, response, authentication) -> {
                            // Clear the SecurityContext after logout
                            SecurityContextHolder.clearContext();
                        })
                        .permitAll()
                )
                .build();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        return new CustomUserDetailsService(userRepository, userRoleRepository);
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setDomainName("");  // Ensure correct domain
        cookieSerializer.setCookieName("JSESSIONID");   // Consistent with logout cookie name
        return cookieSerializer;
    }
}

