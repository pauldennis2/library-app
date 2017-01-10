package com.tiy.web;

import javax.persistence.*;

/**
 * Created by Paul Dennis on 1/10/2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String userName;

    @Column(nullable = false)
    String password;

    public String getUserName () {
        return userName;
    }
}
