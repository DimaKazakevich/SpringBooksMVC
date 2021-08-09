package by.kazakevich.demo.service.impl;

import by.kazakevich.demo.entity.Book;
import by.kazakevich.demo.repository.BookRepository;
import by.kazakevich.demo.service.BookService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public Book findById(Integer id) {
    return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Book update(Book book) {
    Book bookToUpdate = bookRepository.findById(book.getId())
        .orElseThrow(EntityNotFoundException::new);
    bookToUpdate.setAuthor(book.getAuthor());
    bookToUpdate.setTitle(book.getTitle());
    return bookRepository.save(bookToUpdate);
  }

  @Override
  public void save(Book book) {
    bookRepository.save(book);
  }

  @Override
  public void deleteById(Integer id) {
    bookRepository.deleteById(id);
  }
}
