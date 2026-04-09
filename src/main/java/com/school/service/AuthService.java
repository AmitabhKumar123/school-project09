package com.school.service;

import com.school.model.User;
import com.school.repository.UserRepository;
import com.school.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public String login(String username, String password) {

        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return JwtUtil.generateToken(username);
    }

    public User register(User user) {
        return repo.save(user);
    }
}