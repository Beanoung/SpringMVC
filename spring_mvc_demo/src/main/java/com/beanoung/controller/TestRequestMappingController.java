package com.beanoung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 测试@RequestMapping注解各种情况
 */

@Controller
@RequestMapping("/test")
public class TestRequestMappingController {

    // 此时请求路径为/test/hello
    @RequestMapping(
            value = {"/hello","helloworld"},
            method = {
                    RequestMethod.POST,     //新建资源,这两种常用
                    RequestMethod.GET,      //获取资源,这两种常用
                    RequestMethod.DELETE,   //删除资源
                    RequestMethod.PUT       //更新资源
            }
            //params = {"username","!password","age=20","gender!=女"},
            //headers = {"referer"}
    )
    public String success(){
        return "success";
    }

    //测试SpringMVC支持ant风格的路径
    // ?:任意单个字符,不包括"?"本身                                eg. /a?a/test/ant
    // *:任意个数的任意字符,不包括"?"和"/"                          eg. /a*a/test/ant
    // **:任意层数的目录   使用方式只能是:/**/,不允许出现其他字符       eg. /**/test/ant
    @RequestMapping("/**/test/ant")
    public String testAnt(){
        return "success";
    }

    //测试占位符
    @RequestMapping("/rest/{username}/{id}")
    public String testRest(@PathVariable("username") String username,@PathVariable("id") Integer id){
        System.out.println("username:"+username);
        System.out.println("id:"+id);
        return "success";
    }
}

/**
 * params规则:
 * "params":
 * "!params":
 * "params=value":
 * "params!=value":
 *
 * headers规则同params
 */