package com.fatesolo.dao.impl;

import com.fatesolo.dao.BookDao;
import com.fatesolo.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("bookDaoJpaImpl")
public class BookDaoJpaImpl implements BookDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findByNameContaining(String name) {
        return entityManager.createQuery("select b from Book b where b.name like ?", Book.class)
                .setParameter(0, "%" + name + "%")
                .getResultList();
    }

    @Override
    public void save(Book book) {
        entityManager.persist(book);
    }

}
