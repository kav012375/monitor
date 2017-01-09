package com.wulin.biz.common.service;

import org.springframework.stereotype.Service;

/**
 * Created by FengG on 16/6/20.
 */
@Service
public interface CheckService {
    /* 检查输入字符串中是否包含特殊字符
     * 版本:v1.0
     * 作者:冯国阳
     * 日期:2016-06-17
     * 存在特殊字符返回True,不存在返回false
     * */
    boolean IsCheckSpecialCharactors(String input);
    /* 检查输入字符串中是否包含中文
     * 版本:v1.0
     * 作者:冯国阳
     * 日期:2016-06-17
     * 存在特殊字符返回True,不存在返回false
     * */
    boolean IsCheckChineseCharactors(String input);
    /* 移除字符串中所有的特殊字符
     * 版本:v1.0
     * 作者:冯国阳
     * 日期:2016-06-20
     * 返回移除后的字符串
     * */
    String deleteAllSpecialCharactors(String input);
}
