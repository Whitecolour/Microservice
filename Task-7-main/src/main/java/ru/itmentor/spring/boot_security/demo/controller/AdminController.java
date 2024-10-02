package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.Set;

//*
// Сервис для работы с пользователями (предположим, что он у вас есть)
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }
    @GetMapping("/home")
    public String adminHome(Model model) {
        // Здесь вы можете добавить дополнительные данные в модель, если это необходимо
        return "/home/admin_home"; // Имя шаблона, который будет отображён
    }

    @GetMapping // Путь для успешного входа
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin"; // Имя вашего шаблона (например, users.html)
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam Long roleId) { // Измените на Long roleId
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // Хэширование пароля

        // Получение роли из базы данных по ID
        Role userRole = roleService.findById(roleId);
        if (userRole == null) {
            throw new IllegalArgumentException("Role not found with id: " + roleId);
        }
        newUser.setRoles(Set.of(userRole));

        userService.saveOrUpdateUser(newUser);
        return "redirect:/admin"; // перенаправление после добавления
    }
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        User updateUser = userService.saveOrUpdateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        User userDelete = userService.deleteUser(id);
        return "redirect:/";
    }
}