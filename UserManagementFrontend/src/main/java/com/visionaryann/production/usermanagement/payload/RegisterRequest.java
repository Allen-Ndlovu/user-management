package com.visionaryann.production.usermanagement.payload;

public class RegisterRequest {
    public String username, password, email;
    public RegisterRequest(String u, String p, String e){username=u;password=p;email=e;}
}

public class LoginRequest {
    public String username, password;
    public LoginRequest(String u, String p){username=u;password=p;}
}

public class AuthResponse { public String token; }

public class UpdateProfileRequest {
    public String email, password;
    public UpdateProfileRequest(String e, String p){email=e;password=p;}
}
