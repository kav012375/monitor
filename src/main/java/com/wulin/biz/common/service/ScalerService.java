package com.wulin.biz.common.service;


import com.wulin.dal.interfaceRequestLog.dto.InterfaceRequestLogQueryDTO;
import com.wulin.dal.interfaceRequestLog.entity.InterfaceRequestLogDO;

/**
 * Created by zeusw on 2017/1/11.
 */
public interface ScalerService {
    /**
     * 记录请求
     * @param interfaceRequestLogDO
     */
    void scaleRequest(final InterfaceRequestLogDO interfaceRequestLogDO);
    /**
     * 获取请求总次数
     */
    Long getRequestTimesByDay();

    void resetScalerTimes();

    boolean checkDuplicateIp(final InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO) throws Throwable;

}
