package com.tiy.web;

import javax.persistence.*;

/**
 * Created by Paul Dennis on 1/10/2017.
 */

@Entity
@Table(name = "books")
public class Book {

    @ManyToOne
    User user;
//
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String author;

    @Column(nullable = false)
    String genre;




    public Book () {

    }

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
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
