package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.system.exception.CustomException;
import com.system.po.Course;
import com.system.po.SelectedCourse;
import com.system.po.Student;
import com.system.po.Userlogin;
import com.system.service.CourseService;
import com.system.service.SelectCourseService;
import com.system.service.StudentService;
import com.system.service.UserloginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/student")
public class CourseController {
    @Resource
    private UserloginService userloginService;
    @Resource
    private CourseService courseService;

    @Resource
    private SelectCourseService selectedCourseService;

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(@RequestParam(required = false, defaultValue = "1") Integer pageNo, ModelMap map) throws Exception {
        Integer pagesize = 5;
        PageHelper.startPage(pageNo, pagesize);
        List<Course> courseList = courseService.AllCourse();
        PageInfo<Course> coursePageInfo = new PageInfo<>(courseList);
        map.addAttribute("coursePageInfo", coursePageInfo);
        return "student/showCourse";
    }

    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id, HttpSession session, HttpServletRequest request) throws Exception {
        Userlogin userlogin = (Userlogin) session.getAttribute("u");

        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseid(id);
        selectedCourse.setStudentid(Integer.valueOf(userlogin.getUsername()));

        SelectedCourse s = selectedCourseService.findone(selectedCourse);
        String msg = null;
        if (s == null) {
            selectedCourseService.save(selectedCourse);
        } else {
            throw new CustomException("该门课程你已经选了，不能再选");
        }

        return "redirect:/student/selectedCourse";
    }

    @RequestMapping(value = "/outCourse")
    public String outCourse(Integer id) throws Exception {

        selectedCourseService.remove(id);
        return "redirect:/student/selectedCourse";
    }


    @RequestMapping(value = "/passwordReset")
    public String PasswordReset(String oldPassword, String password1, HttpSession session, Model model)throws Exception {
        Userlogin userlogin = (Userlogin) session.getAttribute("u");

        if (!oldPassword.equals(userlogin.getPassword())) {
           throw new CustomException("旧密码不正确");

        } else {
            userlogin.setPassword(password1);
            userloginService.passwordReset(userlogin);
        }
        return "redirect:/logout";
    }

    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model, HttpSession session) throws Exception {
        //获取当前用户名
        Userlogin userlogin = (Userlogin) session.getAttribute("u");

        Student student = studentService.findStudentAndSelectCourseListById(Integer.valueOf(userlogin.getUsername()));
        List<SelectedCourse> list = student.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);
        return "student/selectCourse";
    }

    @RequestMapping(value = "findCourse", method = {RequestMethod.POST})
    private String selectCourse(String findByName, @RequestParam(required = false, defaultValue = "1") Integer pageNo, ModelMap map) throws Exception {
        Integer pagesize = 5;
        PageHelper.startPage(pageNo, pagesize);
        List<Course> courseList = courseService.findCourse(findByName);
        PageInfo<Course> coursePageInfo = new PageInfo<>(courseList);
        map.addAttribute("coursePageInfo", coursePageInfo);
        return "student/showCourse";
    }

    @RequestMapping(value = "/editStudent")
    public String editStudent(@RequestParam Integer id, Model model) throws Exception {

        Student student = studentService.showStudentINfo(id);
        if (student == null) {
              throw new CustomException("未找到该名学生");
        }
        model.addAttribute("student", student);
        return "student/editStudent";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String editStudent(@RequestParam Integer userid, @RequestParam String username, @RequestParam String sex, HttpSession session) throws Exception {
        Student stu = new Student();
        System.out.println(username);
        stu.setUserid(userid);
        stu.setSex(sex);
        stu.setUsername(username);
        studentService.updateStudentInfo(stu);
        //重定向`
        return "redirect:/showStudentInfo";
    }


}
