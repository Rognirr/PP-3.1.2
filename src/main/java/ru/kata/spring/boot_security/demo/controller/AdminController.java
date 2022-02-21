package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private final AdminService userService;
    private final RoleService roleService;
    private final Set<Role> allRoles;

    public AdminController(AdminService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        if (roleService.findAllRoles().isEmpty()) {
            roleService.save(new Role("ADMIN"));
            roleService.save(new Role("USER"));
        }
        this.allRoles = new HashSet<>(roleService.findAllRoles());
    }

    @GetMapping
    public String indexView() {
        return "index";
    }

    @GetMapping("/admin")
    public ModelAndView userView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allUsers");
        modelAndView.addObject("allRoles", allRoles);
        modelAndView.addObject("listUsers", userService.getAllUsers());
        return modelAndView;
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        return "adminEdit";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }
}