package com.tt.teach.controller;

import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import com.tt.teach.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/stu")
public class StudentController extends BaseController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/login")
    public String login() {
        return "/student/login";
    }
    @RequestMapping("/index")
    public String index() {
        String studentName= (String) getSession().getAttribute(SESSION_KEY);
        if (studentName!=null) {
            return "/student/index";
        }
        return REDIRECT+"/stu/login";
    }
    @RequestMapping("/student")
    public String student() {
        return "/student/student";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SESSION_KEY);
        return REDIRECT+"/stu/login";
    }

    @GetMapping(value = "/getStudentList")
    @ResponseBody
    public Object getStudentList() {
        List<Student> list=studentService.getStudentList();
        return list;
//        返回json数据
    }

    @RequestMapping(value = "deleteStudent",method = RequestMethod.POST)
    public String deleteStudent() {
        return "明儿见";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin() {
        String xuehao=getRequest().getParameter("studentNo");
        Integer studentNo=Integer.parseInt(xuehao);
        String loginPwd=getRequest().getParameter("loginPwd");
        Student student=new Student();
        student.setLoginPwd(loginPwd);
        student.setStudentNo(studentNo);
        Student student1=studentService.doLogin(student);
        if (student1!=null){
            getSession().setAttribute(SESSION_KEY,student1.getStudentName());
            return FORWARD+"/stu/index";
        }
        return REDIRECT+"/stu/login";
    }


}
