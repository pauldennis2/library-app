package com.tiy.web;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Paul Dennis on 1/10/2017.
 */
public interface BookRepo extends CrudRepository<Book, Integer> {
    List<Book> findByUser (User user); //Find all books checked out to user
    //List<Book> findByAuthor (String author);

    List<Book> findByUserIsNull ();
    List<Book> findByGenreAndUserIsNull (String genre);

    List<Book> findByAuthorAndUserIsNull (String author);

    @Query("SELECT book FROM Book book WHERE book.title LIKE ?1%")
    List<Book> findByTitleStartsWithAndUserIsNull (String title);
}
