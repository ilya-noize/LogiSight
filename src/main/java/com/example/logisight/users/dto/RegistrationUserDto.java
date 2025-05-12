package com.example.logisight.users.dto;

public record RegistrationUserDto(
        String username,
        String password,
        String confirmPassword,
        String email) {

}
