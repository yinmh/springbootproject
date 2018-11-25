package org.ymh.springbootstudent.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ymh.springbootstudent.dao.StudentMapper;
import org.ymh.springbootstudent.entity.Student;
import org.ymh.springbootstudent.service.StudentService;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Student getStudentByName(String name) {
        return studentMapper.selectByName(name);
    }

    @Override
    public List<Student> selectAllStudent() {

        return studentMapper.getAllStudent();
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }


}
