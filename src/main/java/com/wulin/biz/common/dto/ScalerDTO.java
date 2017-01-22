package com.wulin.biz.common.dto;

import java.sql.Timestamp;

/**
 * Created by zeusw on 2017/1/11.
 */
public class ScalerDTO {
    /**
     * 请求的接口名称
     */
    String interfaceName;
    /**
     * 请求的时间
     */
    Timestamp requestTime;


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
}
