package com.wulin.dal.dailyReport.dto;

/**
 * Created by zeusw on 2017/1/5.
 */
public class DailyRportQueryDTO {
    /**
     * 起始时间
     */
    String startTime;
    /**
     * 结束时间
     */
    String endTime;
    /**
     * 任务实例Id
     */
    String taskInstanceId;
    /**
     * 原始任务Id
     */
    String taskId;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTaskInstanceId() {
        return taskInstanceId;
    }

    public void setTaskInstanceId(String taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
