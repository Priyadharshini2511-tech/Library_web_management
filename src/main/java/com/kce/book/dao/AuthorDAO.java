package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kce.book.bean.AuthorBean;
import com.kce.book.bean.BookBean;


public class AuthorDAO {

    public AuthorBean getAuthor(int authorCode) {

        AuthorBean authorBean = null;
        String query = "SELECT * FROM Author_Tbl WHERE AuthorCode = ?";

        try {
            Connection connection = DBUtil.getDBConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, authorCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                authorBean = new AuthorBean();
                authorBean.setAuthorCode(rs.getInt(1));
                authorBean.setAuthorName(rs.getString(2));
                authorBean.setContactNo(rs.getLong(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorBean;
    }

    public AuthorBean getAuthor(String authorName) {

        AuthorBean authorBean = null;
        String query = "SELECT * FROM Author_Tbl WHERE AuthorName = ?";

        try {
            Connection connection = DBUtil.getDBConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, authorName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                authorBean = new AuthorBean();
                authorBean.setAuthorCode(rs.getInt(1));
                authorBean.setAuthorName(rs.getString(2));
                authorBean.setContactNo(rs.getLong(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorBean;
    }

    public BookBean bookBean(int createBook) {
        // not implemented yet
        return null;
    }
}
