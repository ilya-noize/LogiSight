package com.example.logisight.users.service;

import com.example.logisight.users.db.RoleEntity;
import com.example.logisight.users.db.RoleName;
import com.example.logisight.users.db.RoleRepository;
import com.example.logisight.users.db.UserEntity;
import com.example.logisight.users.db.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        Arrays.stream(RoleName.values())
                .dropWhile(roleRepository::existsByName)
                .forEach(role ->
                        roleRepository.save(new RoleEntity(null, role))
                );
        saveUser(new UserEntity(
                null,
                "admin",
                "admin",
                Set.of(roleRepository.findByName(RoleName.ROLE_ADMIN))
        ));
        saveUser(new UserEntity(
                null,
                "user",
                "user",
                Set.of(roleRepository.findByName(RoleName.ROLE_USER))
        ));
    }

    private void saveUser(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
        }
    }
}
