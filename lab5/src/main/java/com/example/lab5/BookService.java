package com.example.lab5;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Класс для управления книгами, связанный с базой данных PostgreSQL */
@Service
public class BookService {

  @Autowired private BookRepository bookRepository;

  /**
   * Сохранение книги в базе данных
   *
   * @param book
   * @return
   */
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  /**
   * Возвразает список всех книг
   *
   * @return
   */
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  /**
   * Возвращает книгу по ее ID
   *
   * @param updateId
   * @return
   */
  public Optional<Book> getBookById(Long updateId) {
    return bookRepository.findById(updateId);
  }

  /**
   * Обновляет данные о книге в базе данных
   *
   * @param book
   */
  public void updateBook(Book book) {
    bookRepository.save(book);
  }

  /**
   * Удаляет книгу по ее ID
   *
   * @param id
   */
  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }

  /**
   * Находит все книги с заданной ценой и меньше
   *
   * @param price
   * @return
   */
  public List<Book> findBooksByPrice(int price) {
    return bookRepository.findByPriceLessThanEqual(price);
  }
}
