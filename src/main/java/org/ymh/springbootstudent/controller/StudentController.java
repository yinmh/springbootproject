package org.ymh.springbootstudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.ymh.springbootstudent.dao.StudentMapper;
import org.ymh.springbootstudent.entity.Student;
import org.ymh.springbootstudent.service.StudentService;
import org.ymh.springbootstudent.util.ScoreUtils;

import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;

    //添加学生信息
    @RequestMapping(value = "/addstudent",method = RequestMethod.POST)
    public String addStudent(@RequestParam("studentname") String studentName,
                             @RequestParam("math") Integer mathScore,
                             @RequestParam("english") Integer englishScore,
                             @RequestParam("java") Integer javaScore,
                             @RequestParam("computer") Integer computerScore,
                             Map<String, Object> map) {

        Student student1 = new Student();
        Student student = studentService.getStudentByName(studentName);
        if (!studentName.equals("") && student == null) {
            student1.setStudentName(studentName);
            student1.setMathScore(mathScore);
            student1.setEnglishScore(englishScore);
            student1.setJavaScore(javaScore);
            student1.setComputerScore(computerScore);
            int i = studentService.addStudent(student1);
            if (i > 0) {
                map.put("msg", "add success 添加成功");
                return "redirect:/getstudent";
            } else {
                map.put("msg", "add fail 添加失败");
                return "add";

            }
        } else {
            map.put("msg", "add fail 添加失败");
        }

        return "add";
    }

    @RequestMapping(value = "/add")
        public String gotoAdd(){
        return "add";
    }

    @RequestMapping("/edit/{id}")
    public String goedit(@PathVariable("id") Integer id,Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "add";
    }

    //降序输出所有学生信息
    @RequestMapping("/getstudent")
    public String getAllStudent(Model model) {
        List<Student> students = studentService.selectAllStudent();

        List<Student> studentList = ScoreUtils.getStudentList(students);
        model.addAttribute("list", studentList);
        return "dashboard";
    }

    //根据名字查询学生信息
    @RequestMapping(value = "/msgbyname", method = RequestMethod.GET)
    public String getStudentByName(@RequestParam("sname") String StudentName, Model model) {
        Student student = studentService.getStudentByName(StudentName);
        if(student!=null) {
            model.addAttribute("student", student);
        }else{
            model.addAttribute("msg","未找到该学生");
            return "findname";
        }
        return "dashboard1";
    }

    //查询各科平均分
    @RequestMapping(value = "/getavg", method = RequestMethod.GET)
    public String getObjectAvg(@RequestParam("object") String objectName, Map<String, Object> map) {
        List<Student> students = studentService.selectAllStudent();
        int objectAvg = ScoreUtils.getObjectAvg(students, "大学英语");
        map.put("objectname", objectName+"的平均分：");
        map.put("avg", objectAvg);
        return "findavg";
    }
    //修改学生信息
    @RequestMapping("/edit")
    public String updateStudent(@RequestParam("studentname") String studentName,
                                @RequestParam("math") Integer mathScore,
                                @RequestParam("english") Integer englishScore,
                                @RequestParam("java") Integer javaScore,
                                @RequestParam("computer") Integer computerScore){
        Student student = studentService.getStudentByName(studentName);
        student.setMathScore(mathScore);
        student.setComputerScore(computerScore);
        student.setJavaScore(javaScore);
        student.setEnglishScore(englishScore);
        studentMapper.updateByPrimaryKey(student);
        return "redirect:/getstudent";
    }

    @RequestMapping("/select")
    public String goSelect(){
        return "findavg";
    }

    @RequestMapping("/findname")
    public String goFindByName(){
        return "findname";
    }

    @RequestMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        studentMapper.deleteByPrimaryKey(id);
        return "redirect:/getstudent";
    }


}
