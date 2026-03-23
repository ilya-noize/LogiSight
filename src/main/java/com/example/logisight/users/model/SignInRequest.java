package com.example.logisight.users.model;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
