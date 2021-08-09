package by.kazakevich.demo.controller;

import by.kazakevich.demo.entity.Book;
import by.kazakevich.demo.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

  private final BookService bookService;

  @Value("${welcome.message}")
  private String message;

  @Value("${error.message}")
  private String errorMessage;

  @GetMapping({"/", "/index"})
  public ModelAndView index(Model model) {
    model.addAttribute("message", this.message);
    return new ModelAndView("index");
  }

  @GetMapping("/all")
  public ModelAndView getAllBooksPage(Model model) {
    List<Book> books = bookService.findAll();
    model.addAttribute("books", books);
    return new ModelAndView("booklist");
  }

  @GetMapping("/add")
  public ModelAndView getAddBookPage(Model model) {
    model.addAttribute("book", new Book());
    return new ModelAndView("addbook");
  }

  @GetMapping("/edit")
  public ModelAndView getEditPage(Model model, Integer id) {
    Book book = bookService.findById(id);
    model.addAttribute("book", book);
    return new ModelAndView("editbook");
  }

  @PostMapping("/add")
  public ModelAndView add(Model model, @ModelAttribute("book") Book book) {
    String title = book.getTitle();
    String author = book.getAuthor();
    if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(author)) {
      bookService.save(new Book(title, author));
      List<Book> books = bookService.findAll();
      model.addAttribute("books", books);
      return new ModelAndView("redirect:/book/all");
    }

    model.addAttribute("errorMessage", this.errorMessage);
    return new ModelAndView("addbook");
  }

  @PostMapping("/edit")
  public ModelAndView edit(Model model, Book book) {
    if (!StringUtils.isEmpty(book.getAuthor()) && !StringUtils.isEmpty(book.getTitle())) {
      bookService.update(book);
      return new ModelAndView("redirect:/book/all");
    }

    model.addAttribute("errorMessage", this.errorMessage);
    return new ModelAndView("editbook");
  }

  @PostMapping("/delete")
  public ModelAndView delete(Model model, Integer id) {
    bookService.deleteById(id);
    List<Book> books = bookService.findAll();
    model.addAttribute("books", books);
    return new ModelAndView("booklist");
  }
}
