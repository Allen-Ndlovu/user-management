package com.visionaryann.production.usermanagementbackend.controller;

import com.visionaryann.production.usermanagementbackend.model.User;
import com.visionaryann.production.usermanagementbackend.payload.UpdateProfileRequest;
import com.visionaryann.production.usermanagementbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService svc;
    public UserController(UserService s) { svc = s; }

    @GetMapping("/profile")
    public ResponseEntity<User> profile(@AuthenticationPrincipal String username) {
        return ResponseEntity.ok(svc.getProfile(username));
    }

    @PutMapping("/profile")
    public ResponseEntity<User> update(@AuthenticationPrincipal String username,
                                       @RequestBody UpdateProfileRequest r) {
        return ResponseEntity.ok(svc.updateProfile(username, r));
    }
}
