package com.springboot.bean;

import java.util.List;

/**
 * 角色实体
 */
public class Role {

    private Integer id;
    /**
     * 角色名 管理员admin/普通用户user/管理人员等
     */
    private String role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
