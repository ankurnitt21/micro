package com.example.UserService.service;

import com.example.UserService.entity.UserS;
import com.example.UserService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserS user = userRepository.findByUsername(username);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");


        return new org.springframework.security.core.userdetails.User
                (username,
                        user.getPassword(),
                        Collections.singletonList(authority));
    }
}
