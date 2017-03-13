package com.fatesolo.service;

import com.fatesolo.dao.BookDao;
import com.fatesolo.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookService {

    @Resource
    private BookDao bookDao;

    public Book getBookById(int id) {
        return bookDao.findById(id);
    }

    public List<Book> getBooksByName(String name) {
        return bookDao.findByNameContaining(name);
    }

    public boolean addBook(Book book) {
        bookDao.save(book);

        return book.getId() != 0;
    }

}
