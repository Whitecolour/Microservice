package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService; // Внедрение вашего сервиса пользователей
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void authenticateUser(String username, String password) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // Аутентификация успешна
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }
}