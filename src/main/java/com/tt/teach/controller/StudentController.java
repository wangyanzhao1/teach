package com.tt.teach.controller;

import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import com.tt.teach.utils.BaseController;
import com.tt.teach.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/updateStudent")
    public String updateStudent() {
        String xuehao=getRequest().getParameter("stuNo");
        Integer stuNo=Integer.parseInt(xuehao);
        String stuName=getRequest().getParameter("stuName");
        String stuPwd=getRequest().getParameter("stuPwd");
        String stuPhone=getRequest().getParameter("stuPhone");
        Student student=new Student();
        student.setStudentNo(stuNo);
        student.setStudentName(stuName);
        student.setLoginPwd(stuPwd);
        student.setPhone(stuPhone);
        int result=studentService.updateStudent(student);
        if (result>0){
            return "/student/student";
        }
        return "/student/student";

    }

    @RequestMapping(value = "deleteStudent/{stuNo}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteStudent(@PathVariable Integer stuNo) {
        int result=studentService.deleteStudent(stuNo);
        if (result>0){
            return JsonResult.ok("删除成功",result);
        }
        return JsonResult.no("删除失败",result);
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
