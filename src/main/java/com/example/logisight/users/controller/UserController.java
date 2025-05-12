package com.example.logisight.users.controller;

import com.example.logisight.users.annotation.AdminAuthorization;
import com.example.logisight.users.annotation.RootAuthorization;
import com.example.logisight.users.annotation.UserAuthorization;
import com.example.logisight.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Общедоступный endpoint";
    }

    @GetMapping("/user")
    @UserAuthorization
    public String userEndpoint(Principal principal) {

        return "Доступ только для авторизованных пользователей:%n %s"
                .formatted(principal.getName());
    }

    @GetMapping("/admin")
    @AdminAuthorization
    public String adminEndpoint() {
        return "Доступ только для администраторов";
    }

    @GetMapping("/root")
    @RootAuthorization
    public String rootEndpoint() {
        return "Доступ только для суперпользователей";
    }

    @GetMapping
    public String user(Model model, Principal principal) {
        return userService.user(model, principal);
    }
}
