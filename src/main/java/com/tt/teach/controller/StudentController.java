package com.tt.teach.controller;

import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/stu")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/login")
    public String login() {
        return "/student/login";
    }
    @RequestMapping("/index")
    public String index(HttpSession session) {
        String studentName= (String) session.getAttribute("studentName");
        if (studentName!=null) {
            return "/student/index";
        }
        return "/student/login";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpSession session) {
        String xuehao=request.getParameter("studentNo");
        Integer studentNo=Integer.parseInt(xuehao);
        String loginPwd=request.getParameter("loginPwd");
        Student student=new Student();
        student.setLoginPwd(loginPwd);
        student.setStudentNo(studentNo);
        Student student1=studentService.doLogin(student);
        if (student1!=null){
            session.setAttribute("studentName",student1.getStudentName());
            return "/student/index";
        }
        return "/student/login";

    }


}
