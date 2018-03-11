package controller;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Actions.*;

public class StudentController extends HttpServlet {
    private final static String EDIT_STUD = "editStud.jsp";
    private final static String LIST_STUD = "listStud.jsp";
    StudentDao studDao;

    public StudentController() {
        studDao = new StudentDaoImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String view = "";
        if (action.equalsIgnoreCase(LIST.name())) {
            req.setAttribute("students", studDao.listStudents());
            view = LIST_STUD;
        } else if (action.equalsIgnoreCase(EDIT.name())) {
            Student student = studDao.getStudent(Integer.parseInt(req.getParameter("studId")));
            req.setAttribute("student", student);
            view = EDIT_STUD;
        } else if (action.equalsIgnoreCase(DELETE.name())) {
            studDao.delStudent(Integer.parseInt(req.getParameter("studId")));
            req.setAttribute("students", studDao.listStudents());
            view = LIST_STUD;
        } else if (action.equalsIgnoreCase(CREATE.name())) {
            view = LIST_STUD;
        } else {
            throw new RuntimeException("Bad request");
        }
        req.getRequestDispatcher(view).forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }
}
