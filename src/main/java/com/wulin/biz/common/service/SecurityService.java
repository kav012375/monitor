package com.wulin.biz.common.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FengG on 16/6/20.
 */
@Service
public interface SecurityService {
    /* 对字符串进行加密,加密方式为MD5?
     * 版本:v1.0? * 作者:冯国阳?
     * 日期:2016-06-20? *
     * 存在特殊字符返回True,不存在返回false? *
     * */
    String Md5Creator(String input);

    /**
     * 获取用户请求的真实Ip
     * @param httpServletRequest 用户请求体
     * @return
     */
    String GetRealIpAddr(HttpServletRequest httpServletRequest);
}
