//package org.example.resttemplate.service;
//
//import org.example.resttemplate.model.User;
//
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Service
//public class ApiService {
//
//    private static final String BASE_URL = "http://94.198.50.185:7081/api/users";
//    private String sessionId;
//    private final RestTemplate restTemplate;
//
//    public ApiService() {
//        this.restTemplate = new RestTemplate();
//    }
//
//    public void getSessionId() {
//        ResponseEntity<User[]> response = restTemplate.exchange(BASE_URL, HttpMethod.GET, null, User[].class);
//        HttpHeaders headers = response.getHeaders();
//        List<String> cookies = headers.get("Set-Cookie");
//
//        if (cookies != null) {
//            for (String cookie : cookies) {
//                if (cookie.startsWith("JSESSIONID=")) {
//                    sessionId = cookie.split(";")[0];
//                    break;
//                }
//            }
//        }
//    }
//
//
//    public ResponseEntity<User[]> getAllUsers() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", sessionId);
//        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//        return restTemplate.exchange(BASE_URL, HttpMethod.GET, requestEntity, User[].class);
//    }
//
//    public ResponseEntity<User> saveUser(User user) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", sessionId);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
//        return restTemplate.postForEntity(BASE_URL, requestEntity, User.class);
//    }
//
//    public ResponseEntity<User> updateUser(User user) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", sessionId);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
//        return restTemplate.exchange(BASE_URL + "/3", HttpMethod.PUT, requestEntity, User.class);
//    }
//
//    public ResponseEntity<Void> deleteUser() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", sessionId);
//        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//        return restTemplate.exchange(BASE_URL + "/3", HttpMethod.DELETE, requestEntity, Void.class);
//    }
//}
