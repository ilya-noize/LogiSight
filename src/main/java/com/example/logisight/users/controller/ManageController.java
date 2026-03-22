package com.example.logisight.users.controller;

import com.example.logisight.users.controller.annotation.AdminAuthorization;
import com.example.logisight.users.controller.annotation.DriverAuthorization;
import com.example.logisight.users.controller.annotation.ManagerAuthorization;
import com.example.logisight.users.controller.annotation.RootAuthorization;
import com.example.logisight.users.controller.annotation.UserAuthorization;
import com.example.logisight.users.model.UserFullResponse;
import com.example.logisight.users.secure.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManageController {
    private final UserDetailsServiceImpl userService;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Общедоступный endpoint";
    }

    // Мониторинг заказов пользователя
    // Создание заказов
    @GetMapping("/login/{login}")
    @UserAuthorization
    public UserFullResponse getUserAccount(@PathVariable String login) {
        return null;//userService.getUserAccount(login);
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
}
