package com.fatesolo.controller;

import com.fatesolo.entity.Book;
import com.fatesolo.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/book", produces = "application/json;charset=UTF-8")
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);

        return book != null ? book.toString() : "Not Found";
    }

    @RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
    public String getBooksByName(@PathVariable String name) {
        List<Book> books = bookService.getBooksByName(name);

        return books.size() != 0 ? books.toString() : "Not Found";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String addBook(Book book) {
        return bookService.addBook(book) ? book.toString() : "Failure";
    }

}
