package com.springboot.service;

import com.springboot.bean.Employee;

import java.util.List;

public interface EmpService {
    List<Employee> getAll();

    void update(Employee employee);

    Employee getById(Integer id);

    void save(Employee employee);

    void delById(Integer id);
}
