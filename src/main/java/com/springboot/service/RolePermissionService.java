package com.springboot.service;

import java.util.List;

public interface RolePermissionService {

    List<String> getPermsIdByRoleId(Integer roleId);
}
