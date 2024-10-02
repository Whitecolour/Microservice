//package org.example.resttemplate.service;
//
//import org.example.resttemplate.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//@Service
//public class UserService {
//    private final ApiService apiService;
//
//    @Autowired
//    public UserService(ApiService apiService) {
//        this.apiService = apiService;
//    }
//
//    public void performUserOperations() {
//        apiService.getSessionId();
//
//        // Получаем всех пользователей
//        ResponseEntity<User[]> response = apiService.getAllUsers();
//        List<User> users = Arrays.asList(response.getBody());
//        for (User user : users) {
//            System.out.println("User ID: " + user.getId()
//                    + " Name: " + user.getName()
//                    + " Lastname: " + user.getLastName()
//                    + " Age: " + user.getAge());
//        }
//
//        // Сохранение пользователя
//        User newUser = new User(3L, "James", "Brown", (byte) 22);
//        ResponseEntity<User> saveResponse = apiService.saveUser(newUser);
//        System.out.println("Saved user: " + saveResponse.getBody());
//
//        // Обновление пользователя
//        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 22);
//        ResponseEntity<User> updateResponse = apiService.updateUser(updatedUser);
//        System.out.println("Updated user: " + updateResponse.getBody());
//
//        // Удаление пользователя
//        ResponseEntity<Void> deleteResponse = apiService.deleteUser();
//        if (deleteResponse.getStatusCode().is2xxSuccessful()) {
//            System.out.println("Delete user successful");
//        } else {
//            System.out.println("Failed to delete user. Status code: " + deleteResponse.getStatusCode());
//        }
//    }
//}
