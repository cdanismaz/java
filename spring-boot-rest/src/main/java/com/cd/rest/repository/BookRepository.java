package com.cd.rest.repository;

import com.cd.rest.model.Author;
import com.cd.rest.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    public List<Book> getAllBooks() {
        Author author = new Author(3,"John", "Green");
        Book book1 = new Book(author, "Stars", 1950);
        Book book2 = new Book(author, "MK", 2015);
        List<Book> result = new ArrayList<>();
        result.add(book1);
        result.add(book2);
        return result;
    }

    public Book findById(int id) {
        Author author = new Author(3,"John", "Green");
        Book book1 = new Book(author, "Stars", 1950);
        return book1;

    }
}
