package com.cd.rest.mapper;

import com.cd.rest.entitymodel.AuthorEntity;
import com.cd.rest.model.Author;

public class AuthorMapper {
    public static Author toAuthor(AuthorEntity authorEntity) {
        Author author = new Author();
        author.setId(authorEntity.getId());
        author.setName(authorEntity.getName());
        author.setSurname(authorEntity.getSurname());
        author.setBirthyear(authorEntity.getBirthyear());
        return author;
    }

    public static AuthorEntity toAuthorEntity(Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(author.getId());
        authorEntity.setName(author.getName());
        authorEntity.setSurname(author.getSurname());
        authorEntity.setBirthyear(author.getBirthyear());
        return authorEntity;
    }
}
