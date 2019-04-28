package com.cd.rest.controller;

import com.cd.rest.entitymodel.AuthorEntity;
import com.cd.rest.entitymodel.BookEntity;
import com.cd.rest.mapper.BookMapper;
import com.cd.rest.model.Author;
import com.cd.rest.model.Book;
import com.cd.rest.repository.AuthorRepository;
import com.cd.rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

//    @Autowired
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
//
//    @Autowired
//    private AuthorRepository authorRepository;


    public BookController(BookRepository bookrepo, AuthorRepository authorrepo) {
        this.bookRepository = bookrepo;
        this.authorRepository = authorrepo;
    }

    @GetMapping
    //@RequestMapping(value="/list", method = RequestMethod.GET)
    public List<Book> list() {
        final List<BookEntity> allbookentities = bookRepository.findAll();
        List<Book> result = new ArrayList<>();

        for(int i = 0; i< allbookentities.size(); i++) {
            final BookEntity bookEntity = allbookentities.get(i);
            final Book book = BookMapper.toBook(bookEntity);
            result.add(book);
        }
        return result;
    }

    @GetMapping("/{id}")
    public Book getByID(@PathVariable("id") int identifier) {
        final Optional<BookEntity> queryresult = this.bookRepository.findById(identifier);
        if(queryresult.isPresent()) {
            Book book1 = new Book();
            final BookEntity bookEntity = queryresult.get();
            return BookMapper.toBook(bookEntity);
        }
        else {
            return null;
        }
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam("name") String name) {
        return null;
    }

    @PostMapping
    public ResponseEntity saveBookAPI(@RequestBody Book book, HttpServletRequest request) {
//        final String header1 = request.getHeader("cansu");
//        if(header1 == null)
//            return ResponseEntity.badRequest().body(false);
        final BookEntity bookEntity = BookMapper.toBookEntity(book);
        this.authorRepository.save(bookEntity.getAuthor());
        this.bookRepository.save(bookEntity);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        this.bookRepository.deleteById(id);
        return true;
    }
}
