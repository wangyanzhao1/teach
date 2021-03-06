package com.tt.teach.service;

import com.tt.teach.pojo.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentService {
    Student doLogin(Student student);

    List<Student> getStudentList();

    int updateStudent(Student student);

    int deleteStudent(Integer stuNo);

    Student getStuByNo(Integer studentNo);
}
