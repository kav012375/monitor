package com.wulin.filter;

import com.wulin.biz.core.user.service.impl.UserServiceImpl;
import com.wulin.biz.core.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zeusw on 2016/11/13.
 */
public class LoginFilter extends OncePerRequestFilter {
    private UserService userService = new UserServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger("CONSOLE");
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        boolean doFilter = true;
        //配置不需要进行登陆过滤的Uri
        String[] nonFilterUri = new String[]{
          "/html/login", "/images","/addones","/user","/css","/getCode.do","/register","/task/gettask","/file"
        };
        String reqUri = httpServletRequest.getRequestURI();
        /**
         * 判断用户是否在访问主页
         */
        if(reqUri.equals("/")){
            logger.debug("user try to visit index page...");
            httpServletResponse.sendRedirect("/html/index");
            return;
        }
        for (String Uriunit : nonFilterUri){
            if(reqUri.contains(Uriunit)){
                doFilter = false;
                break;
            }
        }
        if(doFilter){
            HttpSession httpSession = null;
            try {
                httpSession = httpServletRequest.getSession();
                if(httpSession == null){
                    httpServletResponse.sendRedirect("/html/login");
                }else{
                    boolean checkResult = userService.checkUserLoginStatus(httpSession);
                    if(!checkResult){
                        httpServletResponse.sendRedirect("/html/login");
                    }else{
                        filterChain.doFilter(httpServletRequest,httpServletResponse);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error(reqUri + "do filter exception in LoginFilter!");
                httpServletResponse.sendRedirect("/html/login");
            }
        }else{
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
