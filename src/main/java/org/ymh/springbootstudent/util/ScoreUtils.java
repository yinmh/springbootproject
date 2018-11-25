package org.ymh.springbootstudent.util;

import org.ymh.springbootstudent.entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class ScoreUtils {
    //获得单科平均分
    public static int getObjectAvg(List<Student> studentList,String objName){
        int avg=0;
        if(objName.equals("应用数学")){
            for (Student student : studentList) {
                avg= avg+student.getMathScore();
            }
        }else if(objName.equals("大学英语")){
            for (Student student : studentList) {
                avg= avg+student.getEnglishScore();
            }
        }else if(objName.equalsIgnoreCase("Java程序设计")){
            for (Student student : studentList) {
                avg= avg+student.getJavaScore();
            }
        }else if(objName.equals("计算机应用基础")){
            for (Student student : studentList) {
                avg= avg+student.getComputerScore();
            }
        }
        return avg/studentList.size();
    }
    //计算平均分
    public static  int getAvg(Student student){
        Integer computerScore = student.getComputerScore();
        Integer englishScore = student.getEnglishScore();
        Integer javaScore = student.getJavaScore();
        Integer mathScore = student.getMathScore();
        return (computerScore+englishScore+javaScore+mathScore)/4;
    }
    //获取平均分最高的学生
    public static Student getMax(List<Student> studentList) {
        int maxIndex=0;
        for (int i = 1; i <studentList.size() ; i++) {
            if(getAvg(studentList.get(maxIndex))<getAvg(studentList.get(i))){
                maxIndex=i;
            }
        }
        return studentList.get(maxIndex);
    }
    //获取平均分最低的学生
    public static Student getMin(List<Student> studentList) {
        int maxIndex=0;
        for (int i = 1; i <studentList.size() ; i++) {
            if(getAvg(studentList.get(maxIndex))>getAvg(studentList.get(i))){
                maxIndex=i;
            }
        }
        return studentList.get(maxIndex);
    }
    //降序输出所有学生信息
    public static List<Student> getStudentList(List<Student> list) {
        TreeSet<Student> students = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int num=-(getAvg(s1)-getAvg(s2));
                int num2=num==0?s1.getStudentName().compareTo(s2.getStudentName()):num;
                return num2;
            }
        });
        for (Student student : list) {
            students.add(student);
        }

        ArrayList<Student> StudentList = new ArrayList<>();
        for (Student student : students) {
            StudentList.add(student);
        }
        return StudentList;
    }
}
