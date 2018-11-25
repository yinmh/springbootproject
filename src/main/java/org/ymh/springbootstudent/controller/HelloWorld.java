package org.ymh.springbootstudent.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.ymh.springbootstudent.entity.Student;
import org.ymh.springbootstudent.service.StudentService;

import java.util.List;
import java.util.Map;

@Controller
@MapperScan("org.ymh.springbootstudent.dao")
public class HelloWorld {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/success")
    public String hello(){
        return "success";
    }
    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/getall")
    @ResponseBody
    public String selectStudentMsg(){
        List<Student> students = studentService.selectAllStudent();
        StringBuffer sb = new StringBuffer();
        for (Student student : students) {
            sb.append(student.toString()).append("\n");
        }
        return sb.toString();
    }
//    @RequestMapping("/getmessage1")
//    public String getStudentTotalScore1(Map<String,Object> map){
//        Student student = studentService.getStudentById(9);
//        System.out.println(student);
//        Integer mathScore = student.getMathScore();
//        Integer englishScore = student.getEnglishScore();
//        Integer javaScore = student.getJavaScore();
//        Integer computerScore = student.getComputerScore();
//        int totalScore = mathScore+englishScore+javaScore+computerScore;
//        map.put("name",student.getStudentName());
//        map.put("math",mathScore);
//        map.put("english",englishScore);
//        map.put("java",javaScore);
//        map.put("comp",computerScore);
//        map.put("total",totalScore);
//        return "success";
//    }
}
