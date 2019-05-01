package com.cd.rest.mapper;

import com.cd.rest.entitymodel.AuthorEntity;
import com.cd.rest.entitymodel.BookEntity;
import com.cd.rest.entitymodel.BookTypeEntity;
import com.cd.rest.model.Author;
import com.cd.rest.model.Book;
import com.cd.rest.model.BookType;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static Book toBook(BookEntity bookEntity) {
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setName(bookEntity.getName());
        book.setPublishYear(bookEntity.getPublishYear());

        final AuthorEntity authorEntity = bookEntity.getAuthor();
        final Author author = AuthorMapper.toAuthor(authorEntity);
        book.setAuthor(author);

        final List<BookTypeEntity> bookTypeEntities = bookEntity.getTypes();
        List<BookType> bookTypes = new ArrayList<>();

        for(int i = 0; i<bookTypeEntities.size(); i++) {
            final BookTypeEntity bookTypeEntity = bookTypeEntities.get(i);
            BookType bookType = BookTypeMapper.toType(bookTypeEntity);
            bookTypes.add(bookType);
        }

        book.setTypes(bookTypes);

        return book;
    }

    public static BookEntity toBookEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(book.getName());
        bookEntity.setPublishYear(book.getPublishYear());
        bookEntity.setId(book.getId());

        //final AuthorEntity authorEntity = AuthorMapper.toAuthorEntity(book.getAuthor());
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(book.getAuthor().getId());
        bookEntity.setAuthor(authorEntity);

        List<BookTypeEntity> bookTypeEntities = new ArrayList<>();

        for(int i = 0; i < book.getTypes().size(); i++) {
            final BookType bookType = book.getTypes().get(i);
            //final BookTypeEntity bookTypeEntity = BookTypeMapper.toTypeEntity(bookType);
            BookTypeEntity bookTypeEntity = new BookTypeEntity();
            bookTypeEntity.setId(bookType.getId());
            bookTypeEntities.add(bookTypeEntity);
        }

        bookEntity.setTypes(bookTypeEntities);

        return bookEntity;
    }

}
