package com.cd.rest.mapper;

import com.cd.rest.entitymodel.AuthorEntity;
import com.cd.rest.entitymodel.BookEntity;
import com.cd.rest.model.Author;
import com.cd.rest.model.Book;

public class BookMapper {
    public static Book toBook(BookEntity bookEntity) {
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setName(bookEntity.getName());
        book.setPublishYear(bookEntity.getPublishYear());
        final AuthorEntity authorEntity = bookEntity.getAuthor();
        final Author author = AuthorMapper.toAuthor(authorEntity);
        book.setAuthor(author);
        return book;
    }

    public static BookEntity toBookEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(book.getName());
        bookEntity.setPublishYear(book.getPublishYear());
        bookEntity.setId(book.getId());
        final AuthorEntity authorEntity = AuthorMapper.toAuthorEntity(book.getAuthor());
        bookEntity.setAuthor(authorEntity);
        return bookEntity;
    }

}
