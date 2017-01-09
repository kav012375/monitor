package com.wulin.dal.taskInstance.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zeusw on 2016/12/21.
 */
public class TaskInstanceDO implements Serializable {
    private static final long serialVersionUID = 458045724375300032L;
    private Long uid;
    private Long taskId;
    private boolean isFinish;
    private Timestamp gmtCreate;
    private Timestamp gmtClosed;
    private boolean isSuccess;
    private String content;
    private String ip;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtClosed() {
        return gmtClosed;
    }

    public void setGmtClosed(Timestamp gmtClosed) {
        this.gmtClosed = gmtClosed;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
