package com.springboot.service.impl;

import com.springboot.bean.Role;
import com.springboot.mapper.UserRoleMapper;
import com.springboot.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<String> getRoleIdsByUserId(Integer userId) {
        return userRoleMapper.getRoleIdsByUserId(userId);
    }
}
