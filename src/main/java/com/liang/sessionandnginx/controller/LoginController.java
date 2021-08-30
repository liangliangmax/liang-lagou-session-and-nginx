package com.liang.sessionandnginx.controller;

import com.liang.sessionandnginx.entity.User;
import com.liang.sessionandnginx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String login(@RequestParam(value = "username",required = false) String username, @RequestParam(value = "password",required = false) String password, HttpServletRequest servletRequest){

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return "用户名或密码不能为空";
        }

        User user = userService.findByUsername(username);

        if(user != null && user.getPassword().equals(password)){
            servletRequest.getSession().setAttribute("user",user);

            return servletRequest.getContextPath()+"登陆成功";
        }
        return "用户名或密码错误";
    }

    @RequestMapping("/getUser")
    public Map getUser(HttpServletRequest servletRequest){

        Object user = servletRequest.getSession().getAttribute("user");

        Map map = new HashMap();
        map.put("user",user);
        map.put("context",servletRequest.getServletContext().getRealPath("/")+":"+servletRequest.getServerPort());
        map.put("sessionId",servletRequest.getSession().getId());
        return  map;
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest servletRequest){

        servletRequest.getSession().invalidate();

        return "退出成功";
    }
}
