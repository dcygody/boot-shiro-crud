package com.springboot.token;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 多realm认证处理
 */
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {

    private Logger logger = LoggerFactory.getLogger(UserModularRealmAuthenticator.class);
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("进入自定义的realm认证器........");
        assertRealmsConfigured(); //判断是否有配置realm, 没有会报异常
        UserToken token = (UserToken) authenticationToken; //自定义token
        String loginType = token.getLoginType(); //判断登录类型
        Collection<Realm> realms = getRealms(); //得到所有的realms
        List<Realm> typeRealms = new ArrayList<>();
        realms.forEach(realm -> {
            if (realm.getName().contains(loginType)) { //根据当前realm(AdminRealm|UserRealm)名, 判断登录的是管理员admin还是普通用户user
                typeRealms.add(realm);
            }
        });
        //根据reals的长度, 调用相应的方法
        if (typeRealms.size() == 1) {
            return doSingleRealmAuthentication(typeRealms.get(0), token);
        } else {
            return doMultiRealmAuthentication(typeRealms, token);
        }
    }


}
