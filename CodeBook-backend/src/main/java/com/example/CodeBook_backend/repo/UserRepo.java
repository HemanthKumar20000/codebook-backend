        package com.example.CodeBook_backend.repo;

import com.example.CodeBook_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByEmail(String email);
}

