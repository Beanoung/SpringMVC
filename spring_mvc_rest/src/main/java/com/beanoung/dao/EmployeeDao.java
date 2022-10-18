package com.beanoung.dao;

import com.beanoung.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> map = null;

    static {
        map = new HashMap<Integer, Employee>();

        map.put(1001, new Employee(1001, "E-AA", "aa@qq.com", 1));
        map.put(1002, new Employee(1002, "E-BB", "bb@qq.com", 1));
        map.put(1003, new Employee(1003, "E-CC", "cc@qq.com", 0));
        map.put(1004, new Employee(1004, "E-DD", "dd@qq.com", 0));
        map.put(1005, new Employee(1005, "E-EE", "ee@qq.com", 1));
    }

    private static Integer initId = 1006;

    public void save(Employee employee){
        //id为空则新增
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        //不为空则修改
        map.put(employee.getId(),employee);
    }

    public Collection<Employee> getAll(){
        return map.values();
    }

    public Employee get(Integer id){
        return map.get(id);
    }

    public void delete(Integer id){
        map.remove(id);
    }
}
