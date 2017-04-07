package com.fatesolo.controller;

import com.fatesolo.entity.Book;
import com.fatesolo.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/xml")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(path = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
    public List<Book> getBooksByName(@PathVariable String name) {
        return bookService.getBooksByName(name);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, consumes = "application/json")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book) ? book : null;
    }

}
