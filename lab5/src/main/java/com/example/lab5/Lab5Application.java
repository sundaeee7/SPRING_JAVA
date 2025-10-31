package com.example.lab5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/** Класс точки входа в программу */
@SpringBootApplication
public class Lab5Application {

  /**
   * Запуск Spring Boot
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(Lab5Application.class, args);
  }

  @Bean
  public CommandLineRunner run(BookService bookService) {
    return args -> {
      System.out.print("http://localhost:8080/");
    };
  }
}
