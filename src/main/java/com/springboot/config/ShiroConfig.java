package com.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.springboot.realm.AdminRealm;
import com.springboot.realm.UserRealm;
import com.springboot.token.UserModularRealmAuthenticator;
import com.springboot.token.UserModularRealmAuthorizer;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.*;

@Configuration
public class ShiroConfig {

    /**
     * 散列次数
     */
    private static final Integer HASH_ITERATIONS = 1024;
    /**
     *加密算法
     */
    private static final String HASH_ALGORITHM_NAME = "MD5";

    /**
     * 自定义realm
     * @return 管理员realm AdminRealm
     */
    @Bean
    public AdminRealm adminRealm() {

        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(hashedCredentialsMatcher()); //加密匹配器
        return adminRealm;
    }

    /**
     * 自定义realm
     * @return 普通用户realm UserRealm
     */
    @Bean
    public UserRealm userRealm() {

        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager() {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        List<Realm> realms = Arrays.asList(adminRealm(), userRealm());
        securityManager.setAuthenticator(modularRealmAuthenticator()); //自定义的多realm认证处理器,顺序, 必须在realm前
        securityManager.setAuthorizer(modularRealmAuthorizer()); //多realm授权管理
        securityManager.setRealms(realms); //自定义realms
        return securityManager;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //数据库异常处理
        mappings.setProperty("DatabaseException", "databaseError");
        mappings.setProperty("UnauthorizedException","/403");
        r.setExceptionMappings(mappings);
        r.setDefaultErrorView("error");
        r.setExceptionAttribute("ex");
        return r;
    }
    /**
     * 自定义多realm认证配置. 注意一定要放到securityManager中
     * @return
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        UserModularRealmAuthenticator userModularRealmAuthenticator = new UserModularRealmAuthenticator();
        userModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return userModularRealmAuthenticator;
    }

    /**
     * 自定义多realm授权操作
     * @return
     */
    @Bean
    public ModularRealmAuthorizer modularRealmAuthorizer() {
       return new UserModularRealmAuthorizer();
    }

    /**
     * 加密功能, 凭证匹配器
     * @return hashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(HASH_ALGORITHM_NAME); //加密算法
        hashedCredentialsMatcher.setHashIterations(HASH_ITERATIONS); //加密次数
        return hashedCredentialsMatcher;
    }

    /**
     * 过滤工厂
     * @return shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuthc");
        shiroFilterFactoryBean.setSuccessUrl("/emps");
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/admin/login", "anon");
        filterMap.put("/user/login", "anon");
//        filterMap.put("/", "anon");
        filterMap.put("/index", "anon");
        filterMap.put("/css/*", "anon");
        filterMap.put("/js/*", "anon");
        filterMap.put("/images/*", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
