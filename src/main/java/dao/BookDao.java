package dao;

import model.Book;

import java.util.List;

public interface BookDao {

   void addBook(Book book);
   void delBook (int bookId);
   void editBook(Book book);
   List<Book> getBookByUserId(int userId);
   Book getBookById (int bookId);
}
