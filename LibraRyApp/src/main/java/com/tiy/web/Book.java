package com.tiy.web;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * Created by Paul Dennis on 1/10/2017.
 */

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String genre;

    @Column(nullable = false)
    String author;

    @ManyToOne
    User user;


    public Book(String title, String genre, String author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        user = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
