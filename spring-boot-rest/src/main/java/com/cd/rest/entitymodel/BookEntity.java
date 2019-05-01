package com.cd.rest.entitymodel;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "books")
@Data
@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int publishYear;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @ManyToMany
    @JoinTable(name = "book_booktypes",
            joinColumns = {@JoinColumn(name = "book_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private List<BookTypeEntity> types;
}
