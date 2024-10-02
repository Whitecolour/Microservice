package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@Controller
public class RegistrationController {


    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration"; // Убедитесь, что ваш файл называется registration.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (!userService.registerUser(user)) { // Используем новый метод
            model.addAttribute("error", "User already exists!"); // Сообщение об ошибке
            return "registration"; // Возврат на страницу регистрации с ошибкой
        }
        model.addAttribute("success", "Registration successful!"); // Сообщение об успехе
        return "redirect:/login"; // Перенаправление на страницу входа после успешной регистрации
    }
}