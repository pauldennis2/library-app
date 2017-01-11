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

    public User () {

    }

    public User (String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName () {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
}
