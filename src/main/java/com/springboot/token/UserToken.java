package com.springboot.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * 自定义token, 实现多个realm的类型判断
 */
public class UserToken extends UsernamePasswordToken implements Serializable {

    private static final long serialVersionUID = 4812793519945855483L;

    /**
     * 登录类型, 管理员还是普通用户
     */
    private String loginType;

    public String getLoginType() {
        return loginType;
    }

    public UserToken(final String username, final String password, String loginType) {

        super(username, password);
        this.loginType = loginType;
    }
}
