package com.fatesolo.dao.impl;

import com.fatesolo.dao.BookDao;
import com.fatesolo.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository("bookDaoJdbcTemplateImpl")
public class BookDaoJdbcTemplateImpl implements BookDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Book findById(int id) {
        String sql = "select name, author from book where id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(id);
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));

            return book;
        });
    }

    @Override
    public List<Book> findByNameContaining(String name) {
        String sql = "select id, name, author from book where name like ?";

        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%"}, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));

            return book;
        });
    }

    @Override
    public void save(Book book) {
        String sql = "insert into book(name, author) values(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());

            return ps;
        }, keyHolder);

        book.setId(keyHolder.getKey().intValue());
    }

}
