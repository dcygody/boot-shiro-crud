package com.springboot.bean;

import java.util.List;

/**
 * 普通用户实体
 */
public class User {

    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

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
