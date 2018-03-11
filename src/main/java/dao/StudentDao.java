package dao;

import model.Student;

import java.util.List;

public interface StudentDao {
    List <Student> listStudents();
    void editStudent(Student student);
    void delStudent(int studId);
    void createStudent(Student student);
    Student getStudent(int studId);
}
