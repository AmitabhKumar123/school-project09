package com.school.controller;

import com.school.model.User;
import com.school.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        String token = service.login(user.getUsername(), user.getPassword());

        return Map.of("token", token);
    }
}