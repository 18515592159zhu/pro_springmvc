package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.Employee;

import java.util.List;

/**
 * @Author zhuchifeng
 * @Date 2022/10/20 7:34
 * @Version 1.0
 */
public interface EmployeeMapper {
    /**
     * 查询所有的员工信息
     * @return
     */
    List<Employee> getEmployeeList();
}
