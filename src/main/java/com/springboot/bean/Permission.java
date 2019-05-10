package com.springboot.bean;

import java.util.List;

/**
 * 权限实体
 */
public class Permission {

    private Integer id;
    /**
     * 权限名
     */
    private String name;
    /**
     * 权限字符串 如:role:create
     */
    private String permission;

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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
