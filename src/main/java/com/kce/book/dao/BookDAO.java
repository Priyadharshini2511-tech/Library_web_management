package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kce.book.bean.BookBean;

public class BookDAO {

    // CREATE BOOK
    public int createBook(BookBean bookBean) {

        int rows = 0;
        String query = "INSERT INTO BOOK_TBL (isbn, book_name, author_code, book_type, cost) VALUES (?,?,?,?,?)";

        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, bookBean.getIsbn());
            ps.setString(2, bookBean.getBookName());
            ps.setInt(3, bookBean.getAuthor().getAuthorCode());
            ps.setString(4, String.valueOf(bookBean.getBookType()));
            ps.setFloat(5, bookBean.getCost());

            rows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    // FETCH BOOK BY ISBN
    public BookBean fetchBook(String isbn) {

        BookBean bookBean = null;
        String query = "SELECT * FROM BOOK_TBL WHERE isbn = ?";

        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bookBean = new BookBean();
                bookBean.setIsbn(rs.getString("isbn"));
                bookBean.setBookName(rs.getString("book_name"));
                bookBean.setBookType(rs.getString("book_type").charAt(0));

                AuthorDAO authorDAO = new AuthorDAO();
                bookBean.setAuthor(authorDAO.getAuthor(rs.getInt("author_code")));

                bookBean.setCost(rs.getFloat("cost"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookBean;
    }
}
