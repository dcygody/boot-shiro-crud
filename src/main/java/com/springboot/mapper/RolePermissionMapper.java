package com.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper {

    List<String> getPermsIdByRoleId(Integer roleId);
}
