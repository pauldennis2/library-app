package com.tiy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Paul Dennis on 1/10/2017.
 */
@Controller
public class LibraryController {

    @Autowired
    UserRepository users;

    @Autowired
    BookRepository books;

    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            User user = new User();
            user.userName = "Zach";
            user.password = "hunter2";
            users.save(user);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home (HttpSession session, Model model, String genre, String author) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getUserName());
            model.addAttribute("name", user.getUserName());
        }
        List<Book> userCheckoutList = books.findByUser(user);
        if (userCheckoutList.size() > 0) {
            model.addAttribute("user-checkout-list", userCheckoutList);
        }

        List<Book> availableCheckoutList;
        if (author != null) {
            availableCheckoutList = books.findByAuthorAndUserIsNull(author);
        } else if (genre != null) {
            availableCheckoutList = books.findByGenreAndUserIsNull(genre);
        } else {
            availableCheckoutList = books.findByUserIsNull();
        }
        if (availableCheckoutList.size() > 0) {
            model.addAttribute("available-checkout-list", availableCheckoutList);
        }
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login (HttpSession session, String userName, String password) {
        User user = users.findFirstByUserName(userName);
        if (user == null) {
            user = new User(userName, password);
            users.save(user);
        } else if (!password.equals(user.getPassword())) {
            throw new WrongPasswordSilly();
        }
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/book-checkout", method = RequestMethod.GET)
    public String checkout (HttpSession session, int bookId) {
        User user = (User) session.getAttribute("user");
        Book book = books.findOne(bookId); //Todo make sure understand
        book.setUser(user);
        books.save(book);
        return "redirect:/";
    }

    @RequestMapping(path = "/book-return", method = RequestMethod.GET)
    public String returnBook (int bookId) {
        Book book = books.findOne(bookId);
        book.setUser(null);
        books.save(book);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-book", method = RequestMethod.POST)
    public String addBook  (String title, String author, String genre) {
        Book book = new Book(title, author, genre);
        books.save(book);
        return "redirect:/";
    }
    
    @RequestMapping(path = "/delete-book", method = RequestMethod.GET)
    public String deleteBook (int bookId) {
        books.delete(bookId);
        return "redirect:/";
    }
}
