package controller;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Actions.*;
import static util.ExeptionMessage.BAD_ACTION;

public class BookController extends HttpServlet {
    private static final String LIST_BOOK = "listBook.jsp";
    private static final String EDIT_BOOK = "editBook.jsp";
    private static final String STUDENT_ID = "studId";
    private static final String BOOKS = "books";
    private static final String BOOK_ID = "bookId";
    private static final String BOOK = "book";
    private static final String NAME = "name";
    private static final String AUTHOR = "author";
    private BookDao bookDao;

    public BookController() {
        bookDao = new BookDaoImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String view = "";
        if (action.equalsIgnoreCase(LIST.name())) {
            int studId = Integer.parseInt(req.getParameter(STUDENT_ID));
            req.setAttribute(BOOKS, bookDao.getBookByUserId(studId));
            req.setAttribute(STUDENT_ID, studId);
            view = LIST_BOOK;
        } else if (action.equalsIgnoreCase(DELETE.name())) {
            int bookId = Integer.parseInt(req.getParameter(BOOK_ID));
            int studId = Integer.parseInt(req.getParameter(STUDENT_ID));
            bookDao.delBook(bookId);
            req.setAttribute(BOOKS, bookDao.getBookByUserId(studId));
            view = LIST_BOOK;
        } else if (action.equalsIgnoreCase(CREATE.name())) {
            int studId = Integer.parseInt(req.getParameter(STUDENT_ID));
            req.setAttribute(STUDENT_ID, studId);
            view = EDIT_BOOK;
        } else if (action.equalsIgnoreCase(EDIT.name())) {
            int bookId = Integer.parseInt(req.getParameter(STUDENT_ID));
            Book book = bookDao.getBookById(bookId);
            req.setAttribute(BOOK, book);
            req.setAttribute(STUDENT_ID, book.getStudentId());
            view = EDIT_BOOK;
        } else {
            throw new RuntimeException(BAD_ACTION);
        }
        req.getRequestDispatcher(view).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        String bookId = req.getParameter(BOOK_ID);
        String studId = req.getParameter(STUDENT_ID);
        book.setStudentId(Integer.parseInt(studId));
        book.setName(req.getParameter(NAME));
        book.setAuthor(req.getParameter(AUTHOR));
        if (bookId.isEmpty() || bookId == null) {
            bookDao.addBook(book);
        } else {
            book.setBookId(Integer.parseInt(bookId));
            bookDao.editBook(book);
        }
        req.setAttribute(BOOKS, bookDao.getBookByUserId(Integer.parseInt(studId)));
        req.getRequestDispatcher(LIST_BOOK).forward(req, resp);
    }
}
