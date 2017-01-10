package com.tiy.web;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paul Dennis on 1/10/2017.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    User findFirstByName (String userName);
}
