package com.example.lab6.model;

import jakarta.persistence.*;
import lombok.Data;

/** Инициализация таблицы ID пользователя с его ролью */
@Entity
@Table(name = "roles")
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20, unique = true)
  private ERole name;

  public Role() {}

  public Role(ERole name) {
    this.name = name;
  }
}
