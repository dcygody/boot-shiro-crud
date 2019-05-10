package com.springboot.service.impl;

import com.springboot.mapper.RolePermissionMapper;
import com.springboot.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public List<String> getPermsIdByRoleId(Integer roleId) {
        return rolePermissionMapper.getPermsIdByRoleId(roleId);
    }
}
