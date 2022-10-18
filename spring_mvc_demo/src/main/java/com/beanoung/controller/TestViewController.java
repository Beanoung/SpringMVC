package com.beanoung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringMVC三种视图,只与视图名称有关
 */

@Controller
@RequestMapping("/view")
public class TestViewController {

    //ThymeleafView: 当视图名称没有前缀的时候,会被视图解析器解析
    //同样作为转发视图,可以渲染页面(因为当前解析器为ThymeleafViewResolver)
    @RequestMapping("/thymeleaf")
    public String testThymeleafView(){
        return "success";
    }

    //InternalResourceView: 视图名称以"forward:"为前缀,此时视图名称不会被SpringMVC配置文件中所配置的视图解析器解析,转发视图
    //只能实现简单的转发,不能渲染页面(因为当前解析器为ThymeleafViewResolver)
    //如果用的是jsp视图解析器,就用这个而不用ThymeleafView
    @RequestMapping("/InternalResourceView")
    public String testInternalResourceView(){
        return "forward:/model";//创建了两个视图,第一个是InternalResourceView,第二个是ThymeleafView
    }

    //RedirectView: 视图名称以"redirect:"为前缀,此时视图名称不会被SpringMVC配置文件中所配置的视图解析器解析,重定向视图
    //重定向地址会变,转发不会变
    //登录成功用重定向,改变地址,登录失败用转发,
    @RequestMapping("/RedirectView")
    public String testRedirectView(){
        return "redirect:/model";
    }
}
