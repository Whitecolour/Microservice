package org.example.resttemplate;


import org.example.resttemplate.model.User;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(RestTemplateApplication.class, args);
//    }

    private static String sessionId;

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder finalCode = new StringBuilder();

        // Получение всех пользователей
        ResponseEntity<User[]> response = restTemplate.getForEntity("http://94.198.50.185:7081/api/users", User[].class);

        // Получение session id
        if (response.getHeaders().containsKey("Set-Cookie")) {
            sessionId = response.getHeaders().get("Set-Cookie").get(0);
        }

        // Добавление пользователя
        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 25); // Возраст на ваш выбор

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<User> requestEntity = new HttpEntity<>(newUser, headers);

        ResponseEntity<String> createResponse = restTemplate.postForEntity("http://94.198.50.185:7081/api/users", requestEntity, String.class);
        finalCode.append(getCodePart(createResponse.getBody())); // Сохранение первой части кода

        // Изменение пользователя
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        HttpEntity<User> updateEntity = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> updateResponse = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.PUT, updateEntity, String.class);
        finalCode.append(getCodePart(updateResponse.getBody())); // Сохранение второй части кода

        // Удаление пользователя
        ResponseEntity<String> deleteResponse = restTemplate.exchange("http://94.198.50.185:7081/api/users/3", HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        finalCode.append(getCodePart(deleteResponse.getBody())); // Сохранение третьей части кода

        // Вывод итогового кода
        System.out.println("Итоговый код: " + finalCode.toString());
        System.out.println("Длина кода: " + finalCode.length());
    }

    private static String getCodePart(String responseBody) {
        // Выводим тело ответа для проверки
        System.out.println("Response Body: " + responseBody);

        // Возвращаем тело ответа, если это не JSON
        return responseBody != null ? responseBody.trim() : "";
    }
}
