package com.springboot.token;

import com.springboot.bean.Administrator;
import com.springboot.bean.User;
import com.springboot.realm.AdminRealm;
import com.springboot.realm.UserRealm;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义授权操作
 */
public class UserModularRealmAuthorizer extends ModularRealmAuthorizer {
    private Logger logger = LoggerFactory.getLogger(UserModularRealmAuthorizer.class);
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        logger.info("进入自定义授权器....");
        assertRealmsConfigured(); //内部调用 getRealm() 方法判断是否有realm配置, 否则抛出异常
        Object primaryPrincipal = principals.getPrimaryPrincipal(); //这个就是我们认证过的subject

        for (Realm realm : getRealms()) {
            if (!(realm instanceof Authorizer)) continue;
            if (primaryPrincipal instanceof Administrator) {
                if (realm instanceof AdminRealm) { //管理员授权
                    return ((AdminRealm) realm).isPermitted(principals, permission);
                }
            }
            if (primaryPrincipal instanceof User) { //普通用户授权
                if (realm instanceof UserRealm) {
                    return ((UserRealm) realm).isPermitted(principals, permission);
                }
            }
        }
        return false;
    }
}
