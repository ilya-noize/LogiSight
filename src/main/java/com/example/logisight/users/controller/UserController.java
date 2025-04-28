package com.example.logisight.users.controller;

import com.example.logisight.users.annotation.AdminAuthorization;
import com.example.logisight.users.annotation.RootAuthorization;
import com.example.logisight.users.annotation.UserAuthorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Общедоступный endpoint";
    }

    @GetMapping("/user")
    @UserAuthorization
    public String userEndpoint() {
        return "Доступ только для авторизованных пользователей";
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
}
