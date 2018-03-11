package dao.impl;

import dao.StudentDao;
import model.Student;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private static Connection connection;

    public StudentDaoImpl() {
        connection = DbUtil.getConnection();
    }

    public List<Student> listStudents() {
        List<Student> list = new ArrayList<Student>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                Student student = new Student();
                student.setDob(rs.getDate("dob"));
                student.setFirstName(rs.getString("firstname"));
                student.setLastName(rs.getString("lastname"));
                student.setEmail(rs.getString("tel"));
                student.setStudId(rs.getInt("id"));
                list.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void editStudent(Student student) {

        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE students SET firstname=?, lastname=?,tel=?, dob=? WHERE id=?");
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setDate(4, (Date) student.getDob()); // (4, new Date (student.getDob().getTime()))
            ps.setInt(5, student.getStudId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delStudent(int studId) {

        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM students WHERE id=?");
            ps.setInt(1, studId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createStudent(Student student) {

        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO students(firstname, lastname, tel, dob) VALUES (?,?,?,?)");
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setDate(4, (Date) student.getDob()); // (4, new Date (student.getDob().getTime()))
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Student getStudent(int studId) {
        Student student = new Student();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM students WHERE id=?");
            ps.setInt(1, studId);
            ResultSet rs = ps.executeQuery();
            student.setEmail(rs.getString("tel"));
            student.setLastName(rs.getString("lastname"));
            student.setFirstName(rs.getString("firstname"));
            student.setDob(rs.getDate("dob"));
            student.setStudId(studId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
