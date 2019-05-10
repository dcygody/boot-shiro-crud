package com.springboot.service;

import com.springboot.bean.Administrator;

import java.util.List;

public interface AdminService {

    public List<Administrator> getAll();

    Administrator getAdminByName(String name);
}
