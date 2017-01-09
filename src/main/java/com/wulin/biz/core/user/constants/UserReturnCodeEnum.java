package com.wulin.biz.core.user.constants;

/**
 * Created by FengG on 16/6/20.
 */
public enum UserReturnCodeEnum {
    USER_INSERT_SUCCESS("用户注册成功",200001),
    USER_HAS_ALREADY_EXIST("用户名已经存在",200002),
    USER_LOGIN_SUCCESS("用户登录成功",200003),
    USER_DOES_NOT_EXIST("用户不存在",200004),
    USER_PWD_IS_WRONG("用户的密码不正确",200005),
    USER_CCODE_IS_NULL_OR_INCORRECT("用户验证码不正确或为空！",200006),
    USER_IP_IS_BAD("用户IP地址很可疑，怀疑代理刷小号，请联系管理员!",200007),
    USER_HAS_NOT_LOGIN("用户未登录",200008),
    USER_HAS_ALREADY_LOGIN("用户已经登录",200009);
    private String description;
    private Integer index;
    //构造方法
    UserReturnCodeEnum(String description, Integer index){
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
