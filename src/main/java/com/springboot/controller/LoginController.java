package com.springboot.controller;

import com.springboot.bean.LoginType;
import com.springboot.service.AdminService;
import com.springboot.token.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/admin/login")
    public String adminLogin(@RequestParam("name") String name,
                            @RequestParam("password") String password,
                            Map<String, Object> map, HttpSession session){

        UserToken token = new UserToken(name, password, LoginType.ADMIN.getType());
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            map.put("msg", "用户名错误");
            e.printStackTrace();
            return "login";
        } catch (IncorrectCredentialsException e) {
            map.put("msg", "密码错误");
            e.printStackTrace();
            return "login";
        }
        session.setAttribute("loginUser", name);
        return "redirect:/emps";
    }

    @RequestMapping("/user/login")
    public String userLogin(@RequestParam("name") String name,
                            @RequestParam("password") String password,
                            Map<String, Object> map, HttpSession session){

        UserToken token = new UserToken(name, password, LoginType.USER.getType());
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            map.put("msg", "用户名错误");
            e.printStackTrace();
            return "login";
        } catch (IncorrectCredentialsException e) {
            map.put("msg", "密码错误");
            e.printStackTrace();
            return "login";
        }
        session.setAttribute("loginUser", name);
        return "redirect:/emps";
    }

    @RequestMapping("/logout")
    public String toLogin() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/noAuthc")
    public String noAuthc() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "error";
    }
}
