package com.springboot.mapper;

import com.springboot.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Select("select * from employee")
    public List<Employee> getAll();

    @Update("update employee set last_name=#{lastName}" +
            ",email=#{email},gender=#{gender},did=#{did},birth=#{birth} " +
            "where id=#{id}")
    public void update(Employee employee);

    @Select("select * from employee where id=#{id}")
    public Employee getById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into employee(last_name, email, gender, did, birth) values(#{lastName}, #{email}, #{gender}, #{did}, #{birth})")
    public void save(Employee employee);

    @Delete("delete from employee where id = #{id}")
    public void delById(Integer id);

}
