package com.atguigu.controller;

import com.atguigu.dao.EmployeeDao;
import com.atguigu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * 查询所有的员工信息-->/employee-->get
 * 跳转到添加页面-->/to/add-->get
 * 新增员工信息-->/employee-->post
 * 跳转到修改页面-->/employee/1-->get
 * 修改员工信息-->/employee-->put
 * 删除员工信息-->/employee/1-->delete
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 查询所有员工数据
     * @param model
     * @return
     */
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model) {
        //获取所有员工信息
        Collection<Employee> employeeList = employeeDao.getAll();
        //将所有的员工信息在请求域中共享
        model.addAttribute("employeeList", employeeList);
        //跳转到列表页面
        return "employee_list";
    }

    /**
     * 执行保存
     * @param employee
     * @return
     */
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        //保存员工信息
        employeeDao.save(employee);
        //重定向到列表页面：/employee
        return "redirect:/employee";
    }

    /**
     * 跳转到更新数据页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee", employee);
        return "employee_update";
    }

    /**
     * 执行更新
     * @param employee
     * @return
     */
    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) {
        //修改员工信息
        employeeDao.save(employee);
        //重定向到列表功能：/employee
        return "redirect:/employee";
    }

    /**
     * 执行删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id) {
        //删除员工信息
        employeeDao.delete(id);
        //重定向到列表功能：/employee
        return "redirect:/employee";
    }
}