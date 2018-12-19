package com.tt.teach.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @作者：朕很萌
 * @时间：2018/12/19 16:13
 * @描述：控制器的工具类
 *
 */

public class BaseController {
//    1.日志打印管理
    private static final Logger logger= LoggerFactory.getLogger(BaseController.class);
//    2.常用的变量提取出来，放到Base中方便使用
    protected static final String SESSION_KEY="studentName";
    protected static final String FORWARD ="forward";
    protected static final String REDIRECT="redirece";

//    3.常用request、session、response的获取

    public HttpServletRequest getRequest() {
        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    public HttpServletResponse getResponse() {
        HttpServletResponse response= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }
    public HttpSession getSession() {
        HttpSession session= getRequest().getSession();
        return session;
    }
}

