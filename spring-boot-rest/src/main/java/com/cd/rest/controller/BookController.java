package com.cd.rest.controller;

import com.cd.rest.model.Book;
import com.cd.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

//    @Autowired
    private BookRepository bookRepository;
//
//    @Autowired
//    private AuthorRepository authorRepository;

    public BookController(BookRepository bookrepo) {
        this.bookRepository = bookrepo;
    }

    @GetMapping
    //@RequestMapping(value="/list", method = RequestMethod.GET)
    public List<Book> list() {
       return bookRepository.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getByID(@PathVariable("id") int identifier) {
        return this.bookRepository.findById(identifier);
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam("name") String name) {
        return null;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Book book, HttpServletRequest request) {
        final String header1 = request.getHeader("cansu");
        if(header1 == null)
            return ResponseEntity.badRequest().body(false);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return true;
    }
}
