package com.visionaryann.production.usermanagementbackend.controller;

import com.visionaryann.production.usermanagementbackend.payload.*;
import com.visionaryann.production.usermanagementbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService svc;
    public AuthController(UserService s) { svc = s; }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Validated @RequestBody RegisterRequest r) {
        return ResponseEntity.ok(svc.register(r));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody LoginRequest r) {
        return ResponseEntity.ok(svc.login(r));
    }
}
