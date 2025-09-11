package com.spring.restfinder.rest_finder.controller;

import com.spring.restfinder.rest_finder.model.User;
import com.spring.restfinder.rest_finder.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("current", "register");

        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/register")
    public String processRegister(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            // Errore: username già usato
            model.addAttribute("userExists", true);
        }

        if (bindingResult.hasErrors() || model.containsAttribute("userExists")) {
            // Ritorna la pagina con tutti gli errori di validazione
            return "admin/register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);
        model.addAttribute("registerSuccess", true);
        model.addAttribute("user", new User());
        return "admin/register";
    }

}
