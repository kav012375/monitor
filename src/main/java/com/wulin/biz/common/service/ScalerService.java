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

    /**
     * 重置记录次数
     */
    void resetScalerTimes();

    /**
     * 检查本次请求的IP是否重复
     * @param interfaceRequestLogQueryDTO
     * @return 重复返回false，不重复返回true
     * @throws Throwable
     */
    boolean checkDuplicateIp(final InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO) throws Throwable;

}
