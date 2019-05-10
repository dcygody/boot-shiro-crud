package com.springboot.mapper;

import com.springboot.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select name, password from user")
    List<User> getAll();

    @Select("select * from user where name = #{name}")
    User getUserByName(String name);

}
