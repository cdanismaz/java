package com.cd.rest.repository;

import com.cd.rest.entitymodel.AuthorEntity;
import com.cd.rest.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

//    public Author findAuthorById(int id) {
//        return new Author(3,"John", "Green");
//    }
}
