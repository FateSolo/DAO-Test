package com.fatesolo.dao.impl;

import com.fatesolo.dao.BookDao;
import com.fatesolo.entity.Book;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("bookDaoJdbcImpl")
public class BookDaoJdbcImpl implements BookDao {

    @Resource
    private DataSource dataSource;

    @Override
    public Book findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "select name, author from book where id = ?";

        try {
            connection = dataSource.getConnection();

            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            rs = statement.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(id);
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));

                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return null;
    }

    @Override
    public List<Book> findByNameContaining(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "select id, name, author from book where name like ?";

        List<Book> books = new ArrayList<>();

        try {
            connection = dataSource.getConnection();

            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return books;
    }

    @Override
    public void save(Book book) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "insert into book(name, author) values(?, ?)";

        try {
            connection = dataSource.getConnection();

            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());

            statement.executeUpdate();

            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

}
