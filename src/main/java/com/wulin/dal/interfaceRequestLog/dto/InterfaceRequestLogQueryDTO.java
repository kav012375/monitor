package com.wulin.dal.interfaceRequestLog.dto;

/**
 * Created by zeusw on 2017/1/11.
 */
public class InterfaceRequestLogQueryDTO {
    String startTime;
    String endTime;
    String mgroup;
    String projectName;
    String ipAddress;

    public InterfaceRequestLogQueryDTO(){
        this.mgroup = "";
        this.projectName = "";
    }

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

    public String getMgroup() {
        return mgroup;
    }

    public void setMgroup(String mgroup) {
        this.mgroup = mgroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
