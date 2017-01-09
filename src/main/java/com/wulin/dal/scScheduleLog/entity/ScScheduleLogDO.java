package com.wulin.dal.scScheduleLog.entity;

import java.sql.Timestamp;

/**
 * Created by zeusw on 2017/1/1.
 */
public class ScScheduleLogDO {
    private Long id;
    private String taskName;
    private Timestamp scheduleTime;
    private String costTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Timestamp getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Timestamp scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }
}
