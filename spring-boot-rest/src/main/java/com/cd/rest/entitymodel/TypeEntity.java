package com.cd.rest.entitymodel;

import lombok.Data;

import javax.persistence.*;

@Table(name = "types")
@Data
@Entity
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
}
