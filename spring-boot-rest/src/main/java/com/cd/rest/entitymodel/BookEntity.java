package com.cd.rest.entitymodel;


import lombok.Data;

import javax.persistence.*;

@Table(name="books")
@Data
@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int publishYear;

    @ManyToOne
    @JoinColumn(name="author_id")
    private AuthorEntity author;

    @OneToMany
    @JoinColumn(name = "book_type")
    private TypeEntity type;
}
