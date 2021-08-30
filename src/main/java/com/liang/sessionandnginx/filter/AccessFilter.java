package com.liang.sessionandnginx.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Component
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String contextPath = httpServletRequest.getContextPath();

        //toLogin地址不拦截
        String requestURI = httpServletRequest.getRequestURI();

        String replaceURI = requestURI.replace(contextPath, "");

        System.out.println(replaceURI);
        if(replaceURI.equals("/login") || replaceURI.equals("/logout")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        Object user = httpServletRequest.getSession().getAttribute("user");

        if(user != null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        httpServletResponse.setHeader("Content-type", "text/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().write("非法访问，请重新登录");

        return;
    }
}
