package com.example.lab6;

import com.example.lab6.model.Book;
import com.example.lab6.model.ERole;
import com.example.lab6.model.Role;
import com.example.lab6.model.User;
import com.example.lab6.repository.RoleRepository;
import com.example.lab6.repository.UserRepository;
import com.example.lab6.service.BookService;

import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Класс точки входа в программу */
@SpringBootApplication
public class lab6Application {

  /**
   * Запуск Spring Boot
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(lab6Application.class, args);
  }

  @Bean
  public CommandLineRunner seedBooks(BookService bookService) {
    return args -> {
      if (bookService.getAllBooks().isEmpty()) {
        bookService.saveBook(new Book("Война и мир", "Лев Толстой", "Роман", 1867, 0));
        bookService.saveBook(new Book("Анна Каренина", "Лев Толстой", "Роман", 1878, 0));
        bookService.saveBook(new Book("48 законов власти", "Роберт Грин", "Психология", 2014, 549));
        bookService.saveBook(new Book("Зов Ктулху", "Говард Лавкрафт", "Ужасы", 1928, 376));
        bookService.saveBook(new Book("Портрет Дориана Грея", "Оскар Уайльд", "Роман", 1890, 313));
        bookService.saveBook(new Book("Мастер и Маргарита", "Михаил Булгаков", "Роман", 1967, 162));
        bookService.saveBook(new Book("Евгений Онегин", "Александр Пушкин", "Роман", 1833, 22));
        bookService.saveBook(new Book("Преступление и наказание", "Фёдор Достоевский", "Роман", 1866, 0));
      }
    };
  }    

  @Bean
  public CommandLineRunner initData(
      UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
    return args -> {
      if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()) {
        roleRepository.save(new Role(ERole.ROLE_USER));
      }
      if (!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()) {
        roleRepository.save(new Role(ERole.ROLE_ADMIN));
      }

      if (!userRepository.existsByUsername("ROLE_ADMIN")) {
        User admin = new User("admin", encoder.encode("admin"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
        roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
        admin.setRoles(roles);
        userRepository.save(admin);
      }
    };
  }
}


