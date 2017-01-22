package com.wulin.dal.interfaceRequestLog.entity;

import java.sql.Timestamp;

/**
 * Created by zeusw on 2017/1/11.
 */
public class InterfaceRequestLogDO {
    Long id;
    String interfaceName;
    Timestamp requestTime;
    String ipAddress;
    String mgroup;
    String projectName;

    public InterfaceRequestLogDO(){
        this.mgroup = "";
        this.projectName = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
}
