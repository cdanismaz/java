package com.cd.rest.mapper;

import com.cd.rest.entitymodel.BookTypeEntity;
import com.cd.rest.model.BookType;

public class BookTypeMapper {
    public static BookType toType(BookTypeEntity bookTypeEntity) {
        BookType type = new BookType();
        type.setId(bookTypeEntity.getId());
        type.setName(bookTypeEntity.getName());
        return type;
    }

    public static BookTypeEntity toTypeEntity(BookType type) {
        BookTypeEntity bookTypeEntity = new BookTypeEntity();
        bookTypeEntity.setId(type.getId());
        bookTypeEntity.setName(type.getName());
        return bookTypeEntity;
    }
}
