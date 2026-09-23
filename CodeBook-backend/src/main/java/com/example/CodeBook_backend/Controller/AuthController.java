
        package com.example.CodeBook_backend.Controller;

import com.example.CodeBook_backend.Service.JwtService;
import com.example.CodeBook_backend.Service.UserService;
import com.example.CodeBook_backend.dto.LoginRequest;
import com.example.CodeBook_backend.model.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserService userService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Users user) {

        Users savedUser = userService.register(user);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                savedUser.getUsername(),
                savedUser.getPassword(),
                java.util.Collections.emptyList()
        );

        String token = jwtService.generateToken(userDetails);

        return Map.of(
                "message", "Registration successful",
                "token", token,
                "user", savedUser
        );
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        Users user = userService.findByUsername(request.getUsername());

        return Map.of(
                "message", "Login successful",
                "token", token,
                "user", user
        );
    }
}

