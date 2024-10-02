package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/home")
    public String getUserHome(Principal principal, Model model) {
        // Вы можете добавить необходимую логику, например, отображение информации
        return "/home/user_home"; // Возвращает представление домашней страницы пользователя
    }

    @GetMapping("/profile")
    public String getUserProfile(Principal principal, Model model) {
        // Получаем имя пользователя из Principal
        String username = principal.getName();
        User user = userService.findUserByUsername(username);

        if (user == null) {
            model.addAttribute("error", "User not found");
            return "error"; // Вернуть представление ошибки
        }

        model.addAttribute("user", user); // Добавляем пользователя в модель
        return "user/profile"; // Вернуть представление профиля пользователя
    }

    // Другие методы для пользователя
}