package com.example.webfluxreactivepostgresdb.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Table("books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    private String title;

    private String author;

    public BookEntity() {
    }

    public BookEntity(String title, String author) {

        this.title = title;
        this.author = author;
    }

    public BookEntity(Long id, String title, String author) {

        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitle() {

        return this.title;
    }

    public String getAuthor() {

        return this.author;
    }

}
