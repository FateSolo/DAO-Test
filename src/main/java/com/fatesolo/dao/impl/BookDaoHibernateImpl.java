package com.fatesolo.dao.impl;

import com.fatesolo.dao.BookDao;
import com.fatesolo.entity.Book;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("bookDaoHibernateImpl")
public class BookDaoHibernateImpl implements BookDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Book findById(int id) {
        return sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    public List<Book> findByNameContaining(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Book where name like ?", Book.class)
                .setParameter(0, "%" + name + "%")
                .list();
    }

    @Override
    public void save(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

}
