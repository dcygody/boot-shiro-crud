package com.springboot.service.impl;

import com.springboot.mapper.RoleMapper;
import com.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Integer> getRoleIdByRoleName(List<String> roles) {

        return roleMapper.getRoleIdByRoleName(roles);
    }

    @Override
    public List<String> getRoleList() {
        return roleMapper.getRoleList();
    }
}
