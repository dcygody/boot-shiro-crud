package com.springboot.service.impl;

import com.springboot.bean.Administrator;
import com.springboot.mapper.AdminMapper;
import com.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminMapper adminMapper;
    @Override
    public List<Administrator> getAll() {
        return adminMapper.getAll();
    }

    @Override
    public Administrator getAdminByName(String name) {
        return adminMapper.getAdminByName(name);
    }
}
