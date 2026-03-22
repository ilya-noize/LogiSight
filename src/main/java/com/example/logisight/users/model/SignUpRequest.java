package com.example.logisight.users.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank
        String username,

        @NotBlank
        @Size(min = 8)
        String password
) {
}
