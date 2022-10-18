package com.beanoung.controller;

import com.beanoung.dao.EmployeeDao;
import com.beanoung.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    //查询所有员工信息
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAll(Model model) {
        Collection<Employee> employee_list = employeeDao.getAll();
        model.addAttribute("employee_list", employee_list);
        return "employee_list";
    }

    //新增员工
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String insertEmployee(Employee employee) {
        employeeDao.save(employee);
        //重定向到列表功能,即/employee
        return "redirect:/employee";
    }

    //跳转修改页面
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee", employee);
        return "employee_update";
    }

    //修改员工信息
    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String update(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    //删除员工信息
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/employee";
    }

}
