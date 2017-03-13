package com.fatesolo.dao;

import com.fatesolo.entity.Book;

import java.util.List;

public interface BookDao {

    /**
     * 查找指定id的书籍
     */
    Book findById(int id);

    /**
     * 查找包含该名字的所有书籍
     */
    List<Book> findByNameContaining(String name);

    /**
     * 保存书籍
     */
    void save(Book book);

}
