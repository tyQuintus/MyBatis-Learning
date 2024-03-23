package com.quintus.mapper;

import com.quintus.pojo.Employee;

public interface EmployeeMapper {
    //根据ID查询员工信息
    Employee queryById(Integer id);

    int deleteById(Integer id);
}
