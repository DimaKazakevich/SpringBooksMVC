package by.kazakevich.demo.service;

import by.kazakevich.demo.entity.Book;
import java.util.List;

public interface BookService {

  List<Book> findAll();

  Book findById(Integer id);

  Book update(Book book);

  void save(Book book);

  void deleteById(Integer id);
}
