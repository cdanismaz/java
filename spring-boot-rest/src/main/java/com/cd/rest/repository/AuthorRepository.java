package com.cd.rest.repository;

import com.cd.rest.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {

    public Author findAuthorById(int id) {
        return new Author(3,"John", "Green");
    }
}
