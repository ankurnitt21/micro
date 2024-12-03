package com.example.UserService.repository;

import com.example.UserService.entity.UserS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserS, Long> {

    UserS findByUsername(String username);
    boolean existsByUsername(String username);
}
