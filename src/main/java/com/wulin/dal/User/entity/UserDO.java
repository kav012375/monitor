package com.wulin.dal.User.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by FengG on 16/6/16.
 */
public class UserDO implements Serializable{
    private static final long serialVersionUID = 458045724375300041L;
    private Integer USER_ID;
    private String USER_ACCT;
    private String USER_PWD;
    private String USER_EMAIL;
    private Date CREATION_TIME;
    private String PHONE_NUM;
    private String TRD_USER_ACCT;
    private String REG_IP;

    public UserDO(){
        CREATION_TIME = new Date();
        REG_IP = "init";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_ACCT() {
        return USER_ACCT;
    }

    public void setUSER_ACCT(String USER_ACCT) {
        this.USER_ACCT = USER_ACCT;
    }

    public String getUSER_PWD() {
        return USER_PWD;
    }

    public void setUSER_PWD(String USER_PWD) {
        this.USER_PWD = USER_PWD;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public Date getCREATION_TIME() {
        return CREATION_TIME;
    }

    public void setCREATION_TIME(Date CREATION_TIME) {
        this.CREATION_TIME = CREATION_TIME;
    }

    public String getPHONE_NUM() {
        return PHONE_NUM;
    }

    public void setPHONE_NUM(String PHONE_NUM) {
        this.PHONE_NUM = PHONE_NUM;
    }

    public String getTRD_USER_ACCT() {
        return TRD_USER_ACCT;
    }

    public void setTRD_USER_ACCT(String TRD_USER_ACCT) {
        this.TRD_USER_ACCT = TRD_USER_ACCT;
    }

    public String getREG_IP() {
        return REG_IP;
    }

    public void setREG_IP(String REG_IP) {
        this.REG_IP = REG_IP;
    }
}
