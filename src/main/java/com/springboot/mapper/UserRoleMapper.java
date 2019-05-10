package com.springboot.mapper;

import com.springboot.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.Max;
import java.util.List;

@Mapper
public interface UserRoleMapper {

    List<String> getRoleIdsByUserId(Integer userId);
}
