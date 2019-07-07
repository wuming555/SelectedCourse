package com.system.controller;

import com.system.po.Student;
import com.system.po.Userlogin;
import com.system.service.StudentService;
import com.system.service.UserloginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Jacey on 2017/6/30.
 */
@Controller
public class LoginController {
    @Resource
    private UserloginService userloginService;
    @Resource
    private StudentService studentService;

    //登录跳转
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginUI() throws Exception {
        return "../../login";
    }

    //登录表单处理
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(Userlogin userlogin, HttpSession session) throws Exception {
        Userlogin u = userloginService.findByNameandpass(userlogin);
        if (u != null) {
            session.setAttribute("u", u);
            return "redirect:/student/showCourse";
        }
        return "../../login";
    }

    @RequestMapping(value = "/showStudentInfo")
    public String StudentInfo(HttpSession session, Model model) {
        Userlogin userlogin = (Userlogin) session.getAttribute("u");
        Student student = studentService.showStudentINfo(Integer.valueOf(userlogin.getUsername()));
        model.addAttribute("student", student);
        return "student/showStudentInfo";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "../../login";
    }

    @RequestMapping(value = "/passwordRest")
    public String toPass() {
        return "student/passwordRest";
    }

}
