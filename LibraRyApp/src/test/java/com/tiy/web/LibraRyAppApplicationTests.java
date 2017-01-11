package com.tiy.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraRyAppApplicationTests {

	@Autowired
	UserRepository users;

	@Autowired
	BookRepository books;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUserRepo() {
		System.out.println("Number of users in the DB: " + users.count());

		User testUser = users.findFirstByUserName("John");
		assertNull(testUser);
	}

	@Test
	public void testBookRepo() {
		System.out.println("Number of books in the DB: " + books.count());

		//List<Book> testList = books.findByAuthor("Frank Lloyd Wright");
		//assertNotNull(testList);
		//assertEquals(0, testList.size());
	}

}
