package com.springboot.service;

import java.util.List;

public interface RoleService {

    List<Integer> getRoleIdByRoleName(List<String> roles);
    List<String> getRoleList();
}
