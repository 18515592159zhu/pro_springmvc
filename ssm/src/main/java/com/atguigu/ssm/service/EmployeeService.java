package com.atguigu.ssm.service;

import com.atguigu.ssm.pojo.Employee;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author zhuchifeng
 * @Date 2022/10/20 7:43
 * @Version 1.0
 */
public interface EmployeeService {
    /**
     * 查询所有的员工信息
     * @return
     */
    List<Employee> getEmployeeList();

    /**
     * 获取员工的分页信息
     * @param pageNum
     * @return
     */
    PageInfo<Employee> getEmployeePage(Integer pageNum);
}
