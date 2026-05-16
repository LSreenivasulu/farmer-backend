package com.example.farmer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.farmer.model.User;
import com.example.farmer.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody User user) {

        Optional<User> existing = repo.findByEmail(user.getEmail());

        if (existing.isPresent()) {
            return Map.of("success", false);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);

        return Map.of("success", true);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {

        Optional<User> existing = repo.findByEmail(user.getEmail());

        if (existing.isPresent() &&
                encoder.matches(user.getPassword(), existing.get().getPassword())) {

            return Map.of(
                    "success", true,
                    "user", existing.get()
            );
        }

        return Map.of("success", false);
    }

}