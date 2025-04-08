package com.visionaryann.production.usermanagementbackend.payload;

import javax.validation.constraints.*;

public class RegisterRequest {
    @NotBlank public String username;
    @NotBlank public String password;
    @Email @NotBlank public String email;
}
