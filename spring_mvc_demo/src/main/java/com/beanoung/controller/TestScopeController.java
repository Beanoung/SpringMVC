package com.beanoung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;



@Controller
public class TestScopeController {

    //向请求域共享数据
    //方式1,ModelAndView
    @RequestMapping("/mav")
    public ModelAndView testMAV(){
        /**
         * ModelAndView包含model和view的功能
         * model:向请求域中共享数据
         * view:设置逻辑视图实现页面跳转
         */
        ModelAndView mav=new ModelAndView();
        //向请求域中共享数据
        mav.addObject("testRequestScope","hello,MAV");
        //设置逻辑视图实现页面跳转
        mav.setViewName("success");
        return mav;
    }

    //方式2,Model
    @RequestMapping("/model")
    public String testModel(Model model){
        System.out.println(model.getClass().getName());
        model.addAttribute("testRequestScope","hello,model");
        return "success";
    }

    //方式3,ModelMap
    @RequestMapping("/modelMap")
    public String testModelMap(ModelMap modelMap){
        System.out.println(modelMap.getClass().getName());
        modelMap.addAttribute("testRequestScope","hello,modelMap");
        return "success";
    }

    //方式4,Map
    @RequestMapping("/map")
    public String testMap(Map<String ,Object> map){
        System.out.println(map.getClass().getName());
        map.put("testRequestScope","hello,map");
        return "success";
    }
    //底层都是BindingAwareModelMap

    //向session域共享数据
    @RequestMapping("/session")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope","hello,session");
        return "success";
    }

    //向application域共享数据
    @RequestMapping("/application")
    public String testAapplication(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("testApplicationScope","hello,application");
        return "success";
    }

}