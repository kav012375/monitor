package com.wulin.dal.LoginSession.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by FengG on 16/6/27.
 */
public class LoginSessionDO implements Serializable {
    private static final long serialVersionUID = 458045724375300042L;
    private Integer SESSION_ID;
    private String USER_ACCT;
    private String USER_SESSION;
    private Date LOGIN_TIME;
    private String IS_LOCKED;

    public LoginSessionDO(){
        LOGIN_TIME = new Date();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSESSION_ID() {
        return SESSION_ID;
    }

    public void setSESSION_ID(Integer SESSION_ID) {
        this.SESSION_ID = SESSION_ID;
    }

    public String getUSER_ACCT() {
        return USER_ACCT;
    }

    public void setUSER_ACCT(String USER_ACCT) {
        this.USER_ACCT = USER_ACCT;
    }

    public String getUSER_SESSION() {
        return USER_SESSION;
    }

    public void setUSER_SESSION(String USER_SESSION) {
        this.USER_SESSION = USER_SESSION;
    }

    public Date getLOGIN_TIME() {
        return LOGIN_TIME;
    }

    public void setLOGIN_TIME(Date LOGIN_TIME) {
        this.LOGIN_TIME = LOGIN_TIME;
    }

    public String getIS_LOCKED() {
        return IS_LOCKED;
    }

    public void setIS_LOCKED(String IS_LOCKED) {
        this.IS_LOCKED = IS_LOCKED;
    }
}
