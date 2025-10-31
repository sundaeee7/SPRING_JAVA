package com.example.lab5;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/** Данный класс Book содержит в себе сеттеры и геттеры для полей + . */
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Название книги не может быть пустым")
  @Size(max = 250, message = "Название книги не должно превышать 250 символов")
  private String title;

  @NotBlank(message = "Автор книги не может быть пустым")
  @Size(max = 250, message = "Автор книги не должен превышать 250 символов")
  private String author;

  @NotBlank(message = "Жанр книги не может быть пустым")
  @Size(max = 250, message = "Жанр книги не должен превышать 250 символов")
  private String genre;

  @Min(value = 0, message = "Год книги должен быть не меньше 0")
  @Max(value = 2025, message = "Год книги не может быть больше 2025")
  private int year;

  @Min(value = 0, message = "Цена книги не может быть отрицательной")
  private int price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Книга{ID="
        + id
        + ", Название='"
        + title
        + '\''
        + ", Автор='"
        + author
        + '\''
        + ", Жанр='"
        + genre
        + '\''
        + ", Год="
        + year
        + ", Цена="
        + price
        + '}';
  }
}
