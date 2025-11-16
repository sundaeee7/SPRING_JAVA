package com.example.lab6.repository;

import com.example.lab6.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** Репозиторий для работы с Book, предоставляет данные через JPA */
public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByPriceLessThanEqual(int price);
}
