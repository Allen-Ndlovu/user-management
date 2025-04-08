package com.visionaryann.production.usermanagementbackend.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @Email @NotBlank @Column(unique = true)
    private String email;

    @NotBlank
    private String role = "USER";

    // getters and setters omitted for brevity
}
