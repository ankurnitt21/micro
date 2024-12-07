package com.example.UserService.service;

import com.example.UserService.entity.User;
import com.example.UserService.entity.UserRole;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserRole userRole = userRoleRepository.findByUserId(user.getUser_id());
        System.out.println(userRole.getRole() + " " + userRole.getUserId());
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+userRole.getRole());
        System.out.println(authority.getAuthority());


        return new org.springframework.security.core.userdetails.User
                (username,
                        user.getPassword(),
                        Collections.singletonList(authority));
    }

}
