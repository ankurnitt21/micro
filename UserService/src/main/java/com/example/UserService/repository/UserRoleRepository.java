package com.example.UserService.repository;

import com.example.UserService.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByUserId(Long userId); // Updated method name to match field name in UserRole
}
