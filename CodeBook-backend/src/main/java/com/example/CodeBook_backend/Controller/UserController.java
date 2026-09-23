
        package com.example.CodeBook_backend.Controller;

import com.example.CodeBook_backend.Service.UserService;
import com.example.CodeBook_backend.model.Users;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://codebook2.netlify.app")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }
}

