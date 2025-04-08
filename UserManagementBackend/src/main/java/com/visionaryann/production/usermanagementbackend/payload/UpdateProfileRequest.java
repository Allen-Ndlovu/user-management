package com.visionaryann.production.usermanagementbackend.payload;

import javax.validation.constraints.*;

public class UpdateProfileRequest {
    @Email @NotBlank public String email;
    @NotBlank public String password;
}
