package com.springboot.bean;

import java.util.List;

/**
 * 管理员实体
 */
public class Administrator {

    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 一个用户拥有多个角色
     */
    private List<Role> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
