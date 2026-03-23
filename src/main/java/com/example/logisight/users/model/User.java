package com.example.logisight.users.model;

import com.example.logisight.users.db.RoleEntity;

import java.util.Set;

public record User(
        Long id,
        String username,
        String password,
        Set<RoleEntity> roles
) {
}
