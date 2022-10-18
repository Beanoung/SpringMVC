package com.beanoung.controller;

import com.beanoung.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class TestAjaxController {

    @RequestMapping("/test/ajax")
    public void testAjax(Integer id, @RequestBody String requestBody, HttpServletResponse response) throws IOException {
        //@RequestBody: 将请求体中的内容与形参绑定
        System.out.println("requestBody:" + requestBody);
        System.out.println("id:" + id);
        response.getWriter().write("hello,axios");
    }

    /**
     * 使用@RequestBody将json格式的请求参数转换为java对象
     * 1.导入jackson的依赖
     * 2.在SpringMVC的配置文件中开启注解启动
     * 3.在处理请求的控制器方法的形参位置,直接设置json格式的请求参数要转换的java类型的形参,使用@RequestBody标识该形参
     */
    //@RequestMapping("/test/RequestBody/json")
    public void testRequestBody(@RequestBody User user, HttpServletResponse response) throws IOException {
        System.out.println("user:" + user);
        response.getWriter().write("hello,RequestBody");
    }

    @RequestMapping("/test/RequestBody/json")
    //Map集合不包括没有值的id,上面实体类包含
    public void testRequestBody(@RequestBody Map<String, Object> map, HttpServletResponse response) throws IOException {
        System.out.println("map:" + map);
        response.getWriter().write("hello,RequestBody");
    }

    /**
     * 注解@ResponseBody: 将所标识的控制器方法的返回值作为响应报文的响应体响应到浏览器
     * 1.导入jackson的依赖
     * 2.在SpringMVC的配置文件中开启注解启动
     * 3.将需要转换为json字符串的java对象直接作为控制器方法的返回值,使用@ResponseBody注解标识该方法,就可以完成转换并响应到浏览器
     */
    @RequestMapping("/test/ResponseBody")
    @ResponseBody
    public String testResponseBody() {
        return "success";
    }

    /**
     * 常用的java对象转换为json对象
     * 实体类 --> json对象
     * map --> json对象
     * list --> json数组
     */

    /*@RequestMapping("/test/ResponseBody/json")
    @ResponseBody
    public User testResponseBodyJson() {
        User user = new User(1001, "张三", "111111", 18, "男");
        return user;
    }*/


    /* @RequestMapping("/test/ResponseBody/json")
    @ResponseBody
    public Map<String,Object> testResponseBodyJson() {
        User user1 = new User(1001, "张三", "1111", 18, "男");
        User user2 = new User(1002, "李四", "2222", 19, "男");
        User user3 = new User(1003, "王五", "3333", 20, "男");
        Map<String,Object> map=new HashMap<>();
        map.put("1001",user1);
        map.put("1002",user2);
        map.put("1003",user3);
        return map;
    } */

    @RequestMapping("/test/ResponseBody/json")
    @ResponseBody
    public List<User> testResponseBodyJson() {
        User user1 = new User(1001, "张三", "1111", 18, "男");
        User user2 = new User(1002, "李四", "2222", 19, "男");
        User user3 = new User(1003, "王五", "3333", 20, "男");
        List<User> list=Arrays.asList(user1,user2,user3);
        return list;
    }

    //注解@RestController(标识在类上)相当于加了两个注解@Controller(给类)和@ResponseBody(给类中每个方法)
}
