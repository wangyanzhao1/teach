package com.tt.teach.service;

import com.tt.teach.pojo.Student;

import javax.servlet.http.HttpServletRequest;

public interface StudentService {
    Student doLogin(Student student);
}
