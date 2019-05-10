package com.springboot.mapper;

import com.springboot.bean.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select name, password from administrator")
    List<Administrator> getAll();

    @Select("select * from administrator where name = #{name}")
    Administrator getAdminByName(String name);

}
