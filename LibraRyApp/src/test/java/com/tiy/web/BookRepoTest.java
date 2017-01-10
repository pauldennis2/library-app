package com.tiy.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Paul Dennis on 1/10/2017.
 */
public class BookRepoTest {

    @Autowired
    BookRepo books;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        books.deleteAll();
    }

    @Test
    public void testFindByGenreUserNull() throws Exception {
        Book book1 = new Book("TestBook", "TestGenre", "TestAuthor");
        book1.setUser(new User());
        books.save(book1);
        Book book2 = new Book("Testy", "TestGenre", "TestAuthor");
        books.save(book2);

        assertEquals(1, books.findByGenreAndUserIsNull("TestGenre").size());
    }

    @Test
    public void testFindByAuthorUserNull() throws Exception {

    }

    @Test
    public void testFindByTitleStartsWithUserNull() throws Exception {
        Book book1 = new Book("TestBook", "TestGenre", "TestAuthor");
        book1.setUser(new User());
        books.save(book1);
        Book book2 = new Book("Testy", "TestGenre", "TestAuthor");
        books.save(book2);

        assertEquals(1, books.findByTitleStartsWithAndUserIsNull("Tes"));
    }

}