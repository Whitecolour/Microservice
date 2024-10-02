package ru.itmentor.spring.boot_security.demo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.exception.RoleNotFoundException;
import ru.itmentor.spring.boot_security.demo.exception.UserNotFoundException;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    private final User  Repository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, EntityManager entityManager, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User saveOrUpdateUser(User user) {
        if (user.getId() != null) {
            if (!userRepository.existsById(user.getId())) {
                throw new UserNotFoundException("User not found with id: " + user.getId());
            }
            // Обновляем существующего пользователя
            User existingUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            existingUser.setUsername(user.getUsername());

            // Выводим сырой и закодированный пароли
            System.out.println("Raw password: " + user.getPassword());
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            System.out.println("Encoded password: " + encodedPassword);

            existingUser.setPassword(encodedPassword);
            existingUser.setRoles(getValidRoles(user.getRoles()));
            return userRepository.save(existingUser);
        } else {
            // Создаем нового пользователя
            if (user.getUsername() == null || user.getPassword() == null) {
                throw new IllegalArgumentException("Username and password must not be null");
            }

            // Выводим сырой и закодированный пароли
            System.out.println("Raw password: " + user.getPassword());
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            System.out.println("Encoded password: " + encodedPassword);

            user.setPassword(encodedPassword);
            user.setRoles(getValidRoles(user.getRoles()));
            return userRepository.save(user);
        }
    }

    private Set<Role> getValidRoles(Set<Role> roles) {
        Set<Role> validRoles = new HashSet<>();
        for (Role role : roles) {
            Role existingRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new RoleNotFoundException("Роль не найдена с ID: " + role.getId()));
            validRoles.add(existingRole);
        }
        return validRoles;
    }

    public User deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Получаем пользователя
            userRepository.deleteById(userId); // Удаляем пользователя
            return user; // Возвращаем удаленного пользователя
        }

        return null; // Или можно выбросить исключение, если пользователь не найден
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean registerUser(User user) {
        // Проверяем, существует ли пользователь с таким именем
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false; // Пользователь уже существует
        }

        // Хэшируем пароль и устанавливаем роли
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER"))); // Установка роли по умолчанию

        userRepository.save(user); // Сохраняем нового пользователя
        return true; // Успешно зарегистрирован
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
