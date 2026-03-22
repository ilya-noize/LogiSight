package com.example.logisight.users.controller;

import com.example.logisight.users.model.UserMapper;
import com.example.logisight.users.model.JwtResponse;
import com.example.logisight.users.model.SignInRequest;
import com.example.logisight.users.model.SignUpRequest;
import com.example.logisight.users.model.User;
import com.example.logisight.users.model.UserResponse;
import com.example.logisight.users.secure.AuthService;
import com.example.logisight.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registration(
            @RequestBody @Valid SignUpRequest request
    ) {
        log.info("Registration");
        User user = userService.registration(userMapper.toDomain(request));

        return userMapper.toResponse(user);
    }

    @PostMapping("/auth")
    public JwtResponse authentication(
            @RequestBody @Valid SignInRequest request
    ) {
        log.debug("Authentication {}",request.username());
        String token = authService.auth(request);

        return new JwtResponse(token);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        log.info("Get by ID={}", id);
        User user = userService.getById(id);

        return userMapper.toResponse(user);
    }

    @GetMapping("/me")
    public UserResponse getMe() {
        log.info("Get me");
        User user = userService.getMe();

        return userMapper.toResponse(user);
    }
}
