package com.example.lab6.controller;

import com.example.lab6.model.User;
import com.example.lab6.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// * Класс, отвечающий за обработку http-запросов на странице регистрированию и логина, связывает html страницы */
@Controller
public class AuthController {

  @Autowired AuthService authService;

  @GetMapping("/login")
  public String loginForm() {
    return "login";
  }

  @GetMapping("/register")
  public String registerForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public String register(
      @Valid @ModelAttribute("user") User userForm, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "register";
    }

    try {
      authService.registerUser(userForm.getUsername(), userForm.getPassword());
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "register";
    }

    return "redirect:/login";
  }
}
