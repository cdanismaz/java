package com.cd.rest.entitymodel;

import lombok.Data;

import javax.persistence.*;

@Table(name = "book_types")
@Data
@Entity
public class BookTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
}
