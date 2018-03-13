package controller;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            int studId = Integer.parseInt(req.getParameter("studId"));
            if(studId != 0) {
                Student student = studDao.getStudent(studId);
                req.setAttribute("student", student);
            } else {
                throw new RuntimeException("Student with id = 0 not exist");
            }
            view = EDIT_STUD;
        } else if (action.equalsIgnoreCase(DELETE.name())) {
            studDao.delStudent(Integer.parseInt(req.getParameter("studId")));
            req.setAttribute("students", studDao.listStudents());
            view = LIST_STUD;
        } else if (action.equalsIgnoreCase(CREATE.name())) {
            view = EDIT_STUD;
        } else {
            throw new RuntimeException("Bad request");
        }
        req.getRequestDispatcher(view).forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();

        student.setFirstName(req.getParameter("firstName"));
        student.setLastName(req.getParameter("lastName"));
        student.setTel(req.getParameter("tel"));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dob"));
            student.setDob(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String stud = req.getParameter("studId");
        if (stud.isEmpty()){
            studDao.createStudent(student);
        }
        else {
            student.setStudId(Integer.parseInt(stud));
            studDao.editStudent(student);
        }
        req.setAttribute("students", studDao.listStudents());
        req.getRequestDispatcher(LIST_STUD).forward(req, resp);

    }
}
