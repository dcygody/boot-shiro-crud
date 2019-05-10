package com.springboot.service.impl;

import com.springboot.bean.Employee;
import com.springboot.mapper.EmpMapper;
import com.springboot.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public void save(Employee employee) {
        empMapper.save(employee);
    }


    @Override
    public List<Employee> getAll() {
        return empMapper.getAll();
    }

    @Override
    public void update(Employee employee) {
       empMapper.update(employee);
    }

    @Override
    public Employee getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void delById(Integer id) {

        empMapper.delById(id);
    }
}
