package com.springboot.realm;

import com.springboot.bean.Administrator;
import com.springboot.bean.User;
import com.springboot.mapper.RoleMapper;
import com.springboot.service.AdminService;
import com.springboot.service.RolePermissionService;
import com.springboot.service.RoleService;
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

/**
 * 管理员realm
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    private static Logger logger = LoggerFactory.getLogger(AdminRealm.class);

    /**
     * 给自定义realm取名
     * @return
     */
    @Override
    public String getName() {
        return "AdminRealm";
    }

    /**
     * 认证操作
     * @param authenticationToken 用户token
     * @return AuthenticationInfo 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("开始管理员认证.......");
        UserToken token = (UserToken) authenticationToken; //获取用户登录token
        Administrator admin = adminService.getAdminByName(token.getUsername()); //获得用户信息

        if (admin == null) {
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes(admin.getName()); //盐值username
        return new SimpleAuthenticationInfo(admin, admin.getPassword(),salt, getName());

    }

    /**
     * 授权操作
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        logger.info("管理员授权....");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> roles = roleService.getRoleList(); //得到所有的角色名
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
