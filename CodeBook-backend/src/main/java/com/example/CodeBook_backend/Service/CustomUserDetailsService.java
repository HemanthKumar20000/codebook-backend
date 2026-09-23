package com.example.CodeBook_backend.Service;


import com.example.CodeBook_backend.dto.CustomUserDetails;
import com.example.CodeBook_backend.model.Users;
import com.example.CodeBook_backend.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo repo;

    public CustomUserDetailsService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users user = repo.findByUsername(username);



        return new CustomUserDetails(user);
    }
}