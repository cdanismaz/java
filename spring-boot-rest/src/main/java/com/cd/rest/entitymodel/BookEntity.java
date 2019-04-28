package com.cd.rest.entitymodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name="books")
@Setter
@Getter
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
}
