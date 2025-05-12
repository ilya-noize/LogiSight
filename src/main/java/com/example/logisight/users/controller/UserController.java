package com.example.logisight.users.controller;

import com.example.logisight.users.annotation.AdminAuthorization;
import com.example.logisight.users.annotation.DriverAuthorization;
import com.example.logisight.users.annotation.ManagerAuthorization;
import com.example.logisight.users.annotation.RootAuthorization;
import com.example.logisight.users.annotation.UserAuthorization;
import com.example.logisight.users.dto.UserFullResponseDto;
import com.example.logisight.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Мониторинг заказов пользователя
    // Создание заказов
    @GetMapping("/login/{login}")
    @UserAuthorization
    public UserFullResponseDto getUserAccount(@PathVariable String login) {
        return userService.getUserAccount(login);
    }

    // Мониторинг входящей/исходящей информации от менеджера
    // Мониторинг рейсов
    @GetMapping("/driver/{id}")
    @DriverAuthorization
    public String getDriverAccount(@PathVariable Long id) {
        return "Driver Panel";
    }

    // Мониторинг входящей/исходящей информации от пользователей и водителей
    // Управление рейсами, грузами
    @GetMapping("/manager/{id}")
    @ManagerAuthorization
    public String getManagerAccount(@PathVariable Long id) {
        return "Manager Panel";
    }

    // Управление доступом для менеджеров и водителей
    // Руководящая позиция в компании
    @GetMapping("/admin/{id}")
    @AdminAuthorization
    public String getAllUserAccount(@PathVariable Long id) {
        return "Admin Panel";
    }

    // Управление доступами всех пользователей - УЛЬТРА режим
    @GetMapping("/root/{id}")
    @RootAuthorization
    public String rootEndpoint(@PathVariable Long id) {
        return "Root Panel";
    }

    @GetMapping
    public String user(Model model, Principal principal) {
        return userService.user(model, principal);
    }
}
