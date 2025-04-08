package com.visionaryann.production.usermanagementbackend.payload;

public class AuthResponse {
    public String token;
    public String type = "Bearer";
    public AuthResponse(String token) { this.token = token; }
}
