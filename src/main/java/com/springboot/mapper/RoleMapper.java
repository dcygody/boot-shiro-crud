package com.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Integer> getRoleIdByRoleName(List<String> roles);

    @Select("select role from role")
    List<String> getRoleList();
}
