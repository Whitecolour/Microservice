//package ru.itmentor.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.itmentor.spring.boot_security.demo.model.User;
//import ru.itmentor.spring.boot_security.demo.service.UserService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/admin")
//public class MyRestController {
//
//    private final UserService userService;
//
//    @Autowired
//    public MyRestController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // Получение всех пользователей
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    // Получение пользователя по ID
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userService.findUserById(id);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
//    }
//
//    // Создание нового пользователя
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        userService.saveOrUpdateUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }
//
//    // Обновление пользователя
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        User existingUser = userService.findUserById(id);
//        if (existingUser == null) {
//            return ResponseEntity.notFound().build();
//        }
//        existingUser.setUsername(user.getUsername());
//        userService.saveOrUpdateUser(existingUser);
//        return ResponseEntity.ok(existingUser);
//    }
//
//    // Удаление пользователя
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        User existingUser = userService.findUserById(id);
//        if (existingUser == null) {
//            return ResponseEntity.notFound().build();
//        }
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
//}