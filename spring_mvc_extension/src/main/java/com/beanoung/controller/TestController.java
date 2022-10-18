package com.beanoung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test/hello")
    public String testLJQ(){

        System.out.println(1/0);    //测试异常
        return "success";
    }
}
