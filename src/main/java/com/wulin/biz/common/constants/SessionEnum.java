package com.wulin.biz.common.constants;

/**
 * Created by zeusw on 2016/9/16.
 */
public enum SessionEnum {
    CurrentLoginUserAcct("2AAA6EE17674E476C37CC33ADE480291","当前已经登录的用户账户",10001),
    CurrentLoginTime("2A9776018CC5ADBCBBA16EAD1FBC46D7","用户登陆的时间",10002),
    CurrentAccessToken("D9BD902F6E735BC9818D89598662F684","用户当前的接入TOKEN",10003);
    private String enumKey;
    private String description;
    private Integer index;

    SessionEnum(String enumKey,String description,Integer index){
        this.enumKey = enumKey;
        this.description = description;
        this.index = index;
    }

    public String getEnumKey() {
        return enumKey;
    }

    public void setEnumKey(String enumKey) {
        this.enumKey = enumKey;
    }

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
