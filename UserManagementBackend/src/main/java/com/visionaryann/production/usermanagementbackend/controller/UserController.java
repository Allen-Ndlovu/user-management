package com.visionaryann.production.usermanagementbackend.controller;

import com.visionaryann.production.usermanagementbackend.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Dummy authentication logic
        if ("admin".equals(user.getUsername()) && "password".equals(user.getPassword())) {
            return "Login successful";
        }
        return "Login failed";
    }
}
