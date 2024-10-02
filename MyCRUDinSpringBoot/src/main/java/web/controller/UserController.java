package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping()
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{page}")
    public String redirectToStatic(@PathVariable("page") String page) {
        // Перенаправляем на статический файл с расширением .html
        return "redirect:/" + page + ".html";
    }

    @GetMapping("/test")
    public String testPage() {
        return "simple";
    }

    @GetMapping("")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }





//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/")
//    public String hello() {
//        return "hello";
//    }
//
//    @GetMapping("/users")
//    public String listUsers(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "usersList";
//    }
//
//    @GetMapping("/users/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("user", new User());
//        return "userForm";
//    }
//
//    @PostMapping("/users/create")
//    public String createUser(@ModelAttribute User user) {
//        userService.saveUser(user);
//        return "redirect:/SpringLastTask_war_exploded/users";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable("id") Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "userForm";
//    }
//
//    @PostMapping("/{id}/edit")
//    public String updateUser(@PathVariable("id") int id, @ModelAttribute User user) {
//        user.setId(id);
//        userService.updateUser(user);
//        return "redirect:/SpringLastTask_war_exploded/users";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/SpringLastTask_war_exploded/users";
//    }
//
//    @GetMapping("/{id}")
//    public String showUserDetails(@PathVariable("id") Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "userDetails";
//    }
}
