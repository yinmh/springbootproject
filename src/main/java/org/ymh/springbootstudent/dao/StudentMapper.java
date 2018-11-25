package org.ymh.springbootstudent.dao;

import org.ymh.springbootstudent.entity.Student;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student selectByName(String name);

    List<Student> getAllStudent();
}