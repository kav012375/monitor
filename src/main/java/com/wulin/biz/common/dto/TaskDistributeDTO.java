package com.wulin.biz.common.dto;

import java.util.List;

/**
 * Created by zeusw on 2016/12/19.
 * 任务下发字段DTO
 */
public class TaskDistributeDTO {
    /**
     * 是否有任务；1-有效任务，0-无任务，2-异常任务
     */
    String Status;
    /**
     *具体的公众号名字
     */
    String mediaName;
    /**
     * 任务的唯一编号
     */
    String taskUid;
    /**
     * 用户名
     */
    String username;
    /**
     * 密码
     */
    String password;
    /**
     *位置
     */
    List<PositionDO> positionDOs;
    /**
     * 动作
     */
    List<ActionDO> actionDOs;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getTaskUid() {
        return taskUid;
    }

    public void setTaskUid(String taskUid) {
        this.taskUid = taskUid;
    }

    public List<PositionDO> getPositionDOs() {
        return positionDOs;
    }

    public void setPositionDOs(List<PositionDO> positionDOs) {
        this.positionDOs = positionDOs;
    }

    public List<ActionDO> getActionDOs() {
        return actionDOs;
    }

    public void setActionDOs(List<ActionDO> actionDOs) {
        this.actionDOs = actionDOs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}