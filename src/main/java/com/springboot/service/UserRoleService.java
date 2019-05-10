package com.springboot.service;

import com.springboot.bean.Role;

import java.util.List;

public interface UserRoleService {

    List<String> getRoleIdsByUserId(Integer userId);
}
