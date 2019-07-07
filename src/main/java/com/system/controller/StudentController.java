package com.system.controller;

import com.system.po.Student;
import com.system.po.Teacher;
import com.system.po.Userlogin;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller

public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/upload")
    public String logout() {
        return "student/upload";
    }

    @RequestMapping(value = "/student/upload")
    public String upload(@RequestParam(value = "uploadfile") MultipartFile uploadfile, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        Userlogin userlogin = (Userlogin) session.getAttribute("u");
        if (uploadfile != null && !uploadfile.isEmpty())
        {
            String dirPath = request.getSession().getServletContext().getRealPath("/upload/");//获得服务器真实路径
            File filePath = new File(dirPath);
            if (!filePath.exists()) {                //如果路径不存在，则创建
                filePath.mkdirs();
            }
            /**
             * 对文件名进行操作防止文件重名
             */
            //1，获取原始文件名
            String originalFilename = uploadfile.getOriginalFilename();
            //2,截取源文件的文件名前缀,不带后缀
            String fileNamePrefix = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            //3,加工处理文件名，原文件加上时间戳
            String newFileNamePrefix = fileNamePrefix + System.currentTimeMillis();
            //4,得到新文件名
            String newFileName = newFileNamePrefix + originalFilename.substring(originalFilename.lastIndexOf("."));
            //5,构建文件对象
            System.out.println(newFileName);
            File file = new File(filePath, newFileName);
            //6,执行上传操作
            System.out.println(file.exists());
            try {
                uploadfile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Student stu=new Student();
            stu.setUserid(userlogin.getUserid());
            stu.setImage(newFileName);
            studentService.upload(stu);
        }
        return "redirect:/showStudentInfo";
    }
}

