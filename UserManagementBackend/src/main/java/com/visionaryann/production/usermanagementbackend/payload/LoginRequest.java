package com.visionaryann.production.usermanagementbackend.payload;

import javax.validation.constraints.*;

public class LoginRequest {
    @NotBlank public String username;
    @NotBlank public String password;
}
