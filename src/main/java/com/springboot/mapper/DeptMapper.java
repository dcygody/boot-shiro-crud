package com.springboot.mapper;

import com.springboot.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from department")
    public List<Department> getAll();
}
