
        package com.example.CodeBook_backend.Service;

import com.example.CodeBook_backend.repo.UserRepo;
import com.example.CodeBook_backend.model.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder) {

        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Users register(Users user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepo.save(user);
    }

    public Users findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public Users findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Users save(Users user) {
        return userRepo.save(user);
    }

    public Users getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}

