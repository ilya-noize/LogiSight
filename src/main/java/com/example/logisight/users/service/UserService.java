package com.example.logisight.users.service;

import com.example.logisight.users.db.RoleEntity;
import com.example.logisight.users.db.RoleName;
import com.example.logisight.users.db.RoleRepository;
import com.example.logisight.users.db.UserEntity;
import com.example.logisight.users.db.UserRepository;
import com.example.logisight.users.model.UserMapper;
import com.example.logisight.users.model.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public User registration(User domain) {
        if (userRepository.existsByUsername(domain.username())) {
            throw new ValidationException("Login already taken");
        }
        UserEntity user = userMapper.toEntity(domain);
        RoleEntity role = roleRepository.findByName(RoleName.ROLE_USER);
        if(user.getRoles().isEmpty()) {
            user.getRoles().add(role);
        }
        user.setPassword(passwordEncoder.encode(domain.password()));
        userRepository.save(user);

        return userMapper.toDomain(user);
    }

    public User getById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not such User ID=%s".formatted(id))
        );
        return userMapper.toDomain(user);
    }

    public User getMe() {
        return null; //TODO: implement me
    }
}
