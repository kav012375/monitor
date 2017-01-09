package com.wulin.biz.common.constants;

/**
 * Created by FengG on 16/6/17.
 */
public enum ErrorCodeEnum {
    USER_ACCT_CANNOT_BE_NULL("用户名不能为空",100001),
    USER_PWD_CANNOT_BE_NULL("用户密码不能为空",100002),
    SYSTEM_ERROR("系统内部错误,请联系客服解决",100000),
    USER_ACCT_CANNOT_HAVE_SPECIAL_CHARACTORS("用户的账户中不能存在特殊字符",100003),
    USER_ACCT_CANNOT_HAVE_CHINESE_CHARACTORS("用户的账户中不能存在中文字符",100004),
    USER_ACCT_LENGTH_IS_TOO_LONG_OR_SHORT("用户的账户长度要在8-24位字符之间",100005),
    USER_PWD_LENGTH_IS_TOO_LONG_OR_SHORT("用户的密码长度要在8-32位字符之间",100006),
    PARAMETER_CHECK_PASS("参数校验通过",100007),
    NO_DAILY_REPROT("所选日期没有报告",100008);

    private String description;
    private Integer index;
    //构造方法
    ErrorCodeEnum(String description, Integer index){
        this.description=description;
        this.index=index;
    }

    //setter and getter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
