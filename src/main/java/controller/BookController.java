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

public class BookController extends HttpServlet {
    private static final String LIST_BOOK = "listBook.jsp";
    private static final String EDIT_BOOK = "listBook.jsp";
    BookDao bookDao;

    BookController() {
        bookDao = new BookDaoImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String view = "";
        if (action.equalsIgnoreCase(LIST.name())) {
            int studId = Integer.parseInt(req.getParameter("studId"));
            req.setAttribute("books", bookDao.getBookByUserId(studId));
            view = LIST_BOOK;
        } else if (action.equalsIgnoreCase(DELETE.name())) {
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            int studId = Integer.parseInt(req.getParameter("studId"));
            bookDao.delBook(bookId);
            req.setAttribute("books", bookDao.getBookByUserId(studId));
            view = LIST_BOOK;
        } else if (action.equalsIgnoreCase(CREATE.name())) {
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            Book book = bookDao.getBookById(bookId);
            view = EDIT_BOOK;
        } else if (action.equalsIgnoreCase(EDIT.name())){
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            Book book = bookDao.getBookById(bookId);
            req.setAttribute("book", book);
            view=EDIT_BOOK;
        }
        req.getRequestDispatcher(view).forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
