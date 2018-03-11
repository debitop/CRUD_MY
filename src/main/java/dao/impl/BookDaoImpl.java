package dao.impl;

import dao.BookDao;
import model.Book;
import util.DbUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private Connection connection;

    public BookDaoImpl() {
        connection = DbUtil.getConnection();
    }

    public void addBook(Book book) {

        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO books (userid, author, title) VALUES (?,?,?)");
            ps.setInt(1, book.getStudentId());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delBook(int bookId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE id=?");
            ps.setInt(1, bookId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editBook(Book book) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE books SET author=?, title=? WHERE id=?");
            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getName());
            ps.setInt(3, book.getBookId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Book> getBookByUserId(int userId) {
        List<Book> list = new ArrayList<Book>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE userid=?");
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("title"));
                book.setBookId(rs.getInt("bookId"));
                book.setStudentId(userId);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book getBookById(int bookId) {
        Book book = new Book();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE id=?");
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book.setAuthor(rs.getString("author"));
                book.setName(rs.getString("title"));
                book.setBookId(bookId);
                book.setStudentId(rs.getInt("userId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
