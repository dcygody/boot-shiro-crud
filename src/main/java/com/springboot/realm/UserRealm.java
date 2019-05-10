package com.springboot.realm;

import com.springboot.bean.Permission;
import com.springboot.bean.Role;
import com.springboot.bean.User;
import com.springboot.mapper.RoleMapper;
import com.springboot.service.RolePermissionService;
import com.springboot.service.RoleService;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import com.springboot.token.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);
    /**
     * ream 名称
     * @return 自定义realm
     */
    @Override
    public String getName() {
        return "UserRealm";
    }

    /**
     * 认证操作
     * @param authenticationToken 用户token
     * @return AuthenticationInfo 认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("开始普通用户认证.......");
        UserToken token = (UserToken) authenticationToken; //获取用户登录token
        User user = userService.getUserByName(token.getUsername()); //获得用户信息
        if (user == null) {
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes(user.getName()); //盐值username
        return new SimpleAuthenticationInfo(user, user.getPassword(),salt, getName());

    }

    /**
     * 授权操作
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("开始普通用户授权.......");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User user1 = userService.getUserByName(user.getName());
        //根据用户id, 查询用户角色集合
        List<String> roles = userRoleService.getRoleIdsByUserId(user1.getId());
        List<Integer> rolesId = roleService.getRoleIdByRoleName(roles); //得到用户角色id集合
        logger.info("角色id: " + rolesId);
        //根据用户角色, 查询用户权限集合
        List<List<String>> perms = new ArrayList<>();
        rolesId.forEach(id -> {
            List<String> perm = rolePermissionService.getPermsIdByRoleId(id);
            perms.add(perm);
        });
        Set<String> permsSet = new HashSet<>();
        for (List<String> list : perms) {
            permsSet.addAll(list);
        }
        logger.info("拥有权限: " + permsSet);
        info.setRoles(new HashSet<>(roles));
        info.setStringPermissions(permsSet);
        return info;
    }


}
