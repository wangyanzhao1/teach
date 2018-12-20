package com.tt.teach.controller;

import com.tt.teach.pojo.Result;
import com.tt.teach.service.ResultService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @作者：朕很萌
 * @时间：2018/12/20 11:22
 * @描述：
 *
 */
@Controller
@RequestMapping("/res")
public class resultController {
    @Resource
    private ResultService resultService;

    @RequestMapping("/result")
    public String result() {
        return "/result/result";
    }

    @GetMapping("/getResultList")
    @ResponseBody
    public Object getResultList() {
        List<Result> list=resultService.getResultList();
        return list;
    }
}
