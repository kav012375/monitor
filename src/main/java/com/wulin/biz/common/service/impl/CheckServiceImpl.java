package com.wulin.biz.common.service.impl;

import com.wulin.biz.common.service.CheckService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by FengG on 16/6/20.
 */
public class CheckServiceImpl implements CheckService {

    private String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    /**
     * 检查输入的字符串中是否存在特殊字符
     * @param input 输入字符串
     * @return 如果存在则返回true
     */
    public boolean IsCheckSpecialCharactors(String input) {

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(input);
        return (m.find());
    }
    /**
     * 检查输入的字符串中是否包含中文
     * @param input
     * @return 存在则返回true
     */
    public boolean IsCheckChineseCharactors(String input) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(input);
        return m.find();
    }
    /**
     * 删除输入字符串中所有的特殊字符
     * @param input
     * @return 返回删除后的字符串
     */
    public String deleteAllSpecialCharactors(String input) {
        input = Pattern.compile(regEx).matcher(input).replaceAll("");
        return input;
    }
}
