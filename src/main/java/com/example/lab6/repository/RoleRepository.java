package com.example.lab6.repository;

import com.example.lab6.model.ERole;
import com.example.lab6.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Репозиторий для работы с Role (ролями), предоставляет данные через JPA */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
