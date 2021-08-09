package by.kazakevich.demo.controller;

import by.kazakevich.demo.entity.Book;
import by.kazakevich.demo.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/book")
@RestController
public class BookRestController {
  private final BookService bookService;

  @GetMapping("/all")
  public List<Book> getAll() {
    return bookService.findAll();
  }

  @GetMapping("/{id}")
  public Book getByID(@PathVariable Integer id) {
    return bookService.findById(id);
  }
}
