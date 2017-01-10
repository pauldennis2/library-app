package com.tiy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Paul Dennis on 1/10/2017.
 */
@Controller
public class LibraryController {

    @Autowired
    UserRepo users;

    @Autowired
    BookRepo books;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home (HttpSession session, Model model) {
        User user = (User) session.getAttribute("userName");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("user-checkout-list", books.findByUser(user));
        model.addAttribute("available-checkout-list", books.findByUserIsNull());
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login (HttpSession session, String userName) {
        session.setAttribute("userName", userName);
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
    public String addBook  (Book book) {
        books.save(book);
        return "redirect:/";
    }
    
    @RequestMapping(path = "/delete-book", method = RequestMethod.GET)
    public String deleteBook (int bookId) {
        books.delete(bookId);
        return "redirect:/";
    }
}
