package com.beanoung.controller;

import com.beanoung.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 测试获取参数
 */

@Controller
public class TestParamController {

    //方法1,通过servletAPI来获取参数,只需要在控制方法的形参里设置HttpServletRequest的形参,就可以通过getParameter方法获取参数
    @RequestMapping("/param/servletAPI")
    public String getParamByServletAPI(HttpServletRequest request){
        HttpSession session = request.getSession();//为了创建cookie
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }

    //方法2,SpringMVC提供的方法,在控制器方法的形参设置与请求参数同名的参数
    @RequestMapping("/param")
    public String getParam(
            @RequestParam("userName") String username,//请求参数名与形参不一致,需要用@RequestParam设置请求参数名
            String password,//与形参一致,不用设置
            @RequestHeader("referer") String referer,//请求头信息与形参绑定
            @CookieValue("JSESSIONID") String jsessionId//cookie数据与形参绑定
    ){
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println("referer:"+referer);
        System.out.println("jsessionId:"+jsessionId);
        return "success";
    }

    //方法3,实体类
    @RequestMapping("/param/pojo")
    public String getParamByPojo(User user){
        System.out.println(user);
        return "success";
    }
}
