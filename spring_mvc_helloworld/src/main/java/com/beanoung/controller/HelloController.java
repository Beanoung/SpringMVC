package com.beanoung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    //请求映射:将浏览器请求映射到该方法执行
    //请求的路径和RequestMapping注解的值一致,就执行该方法
    /**
     * "/"(代表绝对路径):
     * 1.在服务器被解析为localhost:8080/上下文路径/  --> localhost:8080/SpringMVC/
     * 2.在浏览器被解析为不带上下文路径的路径,因此用href和th:href有区别
     */
    @RequestMapping("/")
    public String protal(){
        //将逻辑视图返回,然后会被视图解析器解析 --> 物理视图,然后跳转页面
        return "index";

    }

    @RequestMapping("/hello")
    public String hello(){
        //将逻辑视图返回,然后会被视图解析器解析
        return "success";
    }
}
