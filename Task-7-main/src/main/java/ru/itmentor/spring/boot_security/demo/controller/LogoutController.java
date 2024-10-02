package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout() {
        // Можно добавить логику для выхода пользователя, если нужно
        return "logout"; // Возвращает представление logout.html
    }
}
