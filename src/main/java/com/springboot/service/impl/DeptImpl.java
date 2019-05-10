package com.springboot.service.impl;

import com.springboot.bean.Department;
import com.springboot.mapper.DeptMapper;
import com.springboot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;

    @Override
    public List<Department> getAll() {
        return deptMapper.getAll();
    }
}
