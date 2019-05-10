package com.springboot.bean;

/**
 * 登录类型
 */
public enum LoginType {

    /**
     * 普通用户
     */
    USER("User"),
    /**
     * 管理员
     */
    ADMIN("Admin");
    /**
     * 登录标识
     */
    private String type;

    LoginType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "LoginType{" +
                "type='" + type + '\'' +
                '}';
    }
}
