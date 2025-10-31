package com.example.lab5;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// * Класс, отвечающий за обработку http-запросов и связывает html страницы */
@Controller
public class BookController {

  @Autowired private BookService bookService;

  @GetMapping("/")
  public String home() {
    return "index";
  }

  @GetMapping("/books")
  public String listBooks(Model model) {
    model.addAttribute("books", bookService.getAllBooks());
    return "list";
  }

  @GetMapping("/add")
  public String addBookForm(Model model) {
    model.addAttribute("book", new Book());
    return "add";
  }

  @PostMapping("/add")
  public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "add";
    }
    bookService.saveBook(book);
    return "redirect:/books";
  }

  @GetMapping("/edit/{id}")
  public String editBookForm(@PathVariable Long id, Model model) {
    Book book =
        bookService
            .getBookById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book Id"));
    model.addAttribute("book", book);
    return "edit";
  }

  @PostMapping("/edit/{id}")
  public String updateBook(
      @PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult result) {
    if (result.hasErrors()) {
      return "edit";
    }
    book.setId(id);
    bookService.updateBook(book);
    return "redirect:/books";
  }

  @GetMapping("/delete/{id}")
  public String deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return "redirect:/books";
  }

  @GetMapping("/search")
  public String searchForm() {
    return "search";
  }

  @PostMapping("/search")
  public String searchBooks(@RequestParam int maxPrice, Model model) {
    List<Book> books = bookService.findBooksByPrice(maxPrice);
    model.addAttribute("books", books);
    model.addAttribute("maxPrice", maxPrice);
    return "search";
  }
}
