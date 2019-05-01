package com.cd.rest.entitymodel;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "authors")
@Entity
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(name="lastname")
    private String surname;

    private int birthyear;
}
