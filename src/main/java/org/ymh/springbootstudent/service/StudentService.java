package org.ymh.springbootstudent.service;


import org.ymh.springbootstudent.entity.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(Integer id);
    Student getStudentByName(String name);
    List<Student> selectAllStudent();
    int addStudent(Student student);

}
