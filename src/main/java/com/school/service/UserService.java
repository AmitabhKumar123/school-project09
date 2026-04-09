package com.school.service;

import com.school.model.User;
import com.school.repository.UserRepository;
import com.school.security.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // 🔐 hash password
        return repo.save(user);
    }

    public String login(String username, String password) {

        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return JwtUtil.generateToken(user.getUsername(), user.getRole().name());
    }
    
    
    
    
}