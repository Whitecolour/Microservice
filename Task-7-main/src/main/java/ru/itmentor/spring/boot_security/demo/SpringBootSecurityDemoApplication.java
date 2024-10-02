package ru.itmentor.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itmentor.spring.boot_security.demo.configs.MvcConfig;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);

//        // Создаем экземпляр BCryptPasswordEncoder
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        // Пароль, который нужно закодировать
//        String userPassword = "456";
//        String adminPassword = "123";
//
//
//        // Кодируем пароль
//        String encodedPassword = passwordEncoder.encode(userPassword);
//        String encoded1Password = passwordEncoder.encode(adminPassword);
//
//        // Выводим закодированный пароль
//        System.out.println("Encoded Password: " + encodedPassword);
//        System.out.println("Encoded Password1: " + encoded1Password);


	}

}
