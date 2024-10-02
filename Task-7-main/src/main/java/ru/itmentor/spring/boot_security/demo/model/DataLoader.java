//package ru.itmentor.spring.boot_security.demo.model;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
//import ru.itmentor.spring.boot_security.demo.repository.UserRepository;
//
//import java.util.Collections;
//@Component
//public class DataLoader implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//
//    @Autowired
//    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Сначала создайте роли
//        Role adminRole = new Role("ROLE_ADMIN");
//        Role userRole = new Role("ROLE_USER");
//        roleRepository.save(adminRole);
//        roleRepository.save(userRole);
//
//        // Затем создайте пользователей
//        User admin = new User("admin", passwordEncoder.encode("admin"), Collections.singleton(adminRole));
//        User user = new User("user", passwordEncoder.encode("user"), Collections.singleton(userRole));
//
//        userRepository.save(admin);
//        userRepository.save(user);
//    }
//}
