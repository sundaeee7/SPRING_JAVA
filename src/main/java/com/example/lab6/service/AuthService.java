package com.example.lab6.service;

import com.example.lab6.model.ERole;
import com.example.lab6.model.Role;
import com.example.lab6.model.User;
import com.example.lab6.repository.RoleRepository;
import com.example.lab6.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** Класс аутентифицирующий и регистрирующий пользователей */
@Service
public class AuthService {

  @Autowired UserRepository userRepository;

  @Autowired RoleRepository roleRepository;

  @Autowired PasswordEncoder encoder;

  public void registerUser(String username, String password) {
    if (userRepository.existsByUsername(username)) {
      throw new RuntimeException("Пользователь уже зарегистрирован");
    }

    User user = new User(username, encoder.encode(password));

    Set<Role> roles = new HashSet<>();
    Role userRole =
        roleRepository
            .findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("У пользователя нет роли"));
    roles.add(userRole);

    user.setRoles(roles);
    userRepository.save(user);
  }
}
