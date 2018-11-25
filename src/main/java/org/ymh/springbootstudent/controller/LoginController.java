package org.ymh.springbootstudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @RequestMapping(value = "/manage/login",method = RequestMethod.GET)
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")  String password,
                        Map<String,Object> map, HttpSession session){
        if(!username.equals("") && password.equals("ymhymh")){
            session.setAttribute("loginUser",username);
            return "redirect:/getstudent";
//            return "management";
        }else {
            map.put("msg","用户名密码不正确");
            return "login";
        }
    }
}
