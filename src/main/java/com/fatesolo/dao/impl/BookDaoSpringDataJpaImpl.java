package com.fatesolo.dao.impl;

import com.fatesolo.dao.BookDao;
import com.fatesolo.entity.Book;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository("bookDaoSpringDataJpaImpl")
@RepositoryDefinition(domainClass = Book.class, idClass = Integer.class)
public interface BookDaoSpringDataJpaImpl extends BookDao {
}
