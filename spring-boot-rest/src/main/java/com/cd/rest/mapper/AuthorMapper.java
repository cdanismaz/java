package com.cd.rest.mapper;

import com.cd.rest.entitymodel.AuthorEntity;
import com.cd.rest.entitymodel.TypeEntity;
import com.cd.rest.model.Author;
import com.cd.rest.model.Types;

public class AuthorMapper {
    public static Author toAuthor(AuthorEntity authorEntity) {
        Author author = new Author();
        author.setId(authorEntity.getId());
        author.setName(authorEntity.getName());
        author.setSurname(authorEntity.getSurname());
        author.setBirthyear(authorEntity.getBirthyear());

        final TypeEntity typeEntity = authorEntity.getType();
        Types types = TypeMapper.toType(typeEntity);
        author.setAuthortype(types);
        return author;
    }

    public static AuthorEntity toAuthorEntity(Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(author.getId());
        authorEntity.setName(author.getName());
        authorEntity.setSurname(author.getSurname());
        authorEntity.setBirthyear(author.getBirthyear());

        final TypeEntity typeEntity = TypeMapper.toTypeEntity(author.getAuthortype());
        authorEntity.setType(typeEntity);

        return authorEntity;
    }
}
