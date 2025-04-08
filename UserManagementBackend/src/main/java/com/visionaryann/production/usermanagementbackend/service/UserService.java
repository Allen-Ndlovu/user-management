package com.visionaryann.production.usermanagementbackend.service;

import com.visionaryann.production.usermanagementbackend.model.User;
import com.visionaryann.production.usermanagementbackend.payload.*;
import com.visionaryann.production.usermanagementbackend.repository.UserRepository;
import com.visionaryann.production.usermanagementbackend.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtUtil jwt;

    public UserService(UserRepository r, JwtUtil j) { repo = r; jwt = j; }

    public AuthResponse register(RegisterRequest req) {
        User u = new User();
        u.setUsername(req.username);
        u.setPassword(encoder.encode(req.password));
        u.setEmail(req.email);
        repo.save(u);
        return new AuthResponse(jwt.generateToken(u.getUsername()));
    }

    public AuthResponse login(LoginRequest req) {
        User u = repo.findByUsername(req.username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!encoder.matches(req.password, u.getPassword()))
            throw new RuntimeException("Invalid credentials");
        return new AuthResponse(jwt.generateToken(u.getUsername()));
    }

    public User getProfile(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateProfile(String username, UpdateProfileRequest req) {
        User u = getProfile(username);
        u.setEmail(req.email);
        u.setPassword(encoder.encode(req.password));
        return repo.save(u);
    }
}
