package com.wulin.biz.common.utils;

import com.wulin.dal.interfaceRequestLog.dto.InterfaceRequestLogQueryDTO;
import com.wulin.dal.task.entity.TaskDO;

import java.sql.Timestamp;

/**
 * Created by zeusw on 2017/1/22.
 */
public class ScalerUtils {

    public static InterfaceRequestLogQueryDTO assembleInterfaceRequestLogQyueryUtil(TaskDO taskDO, String ipAddress)
            throws Throwable {
        InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO = new InterfaceRequestLogQueryDTO();
        Timestamp queryTime = new Timestamp(System.currentTimeMillis());
        interfaceRequestLogQueryDTO.setEndTime(queryTime.toString().split(" ")[0] + " 23:59:59");
        interfaceRequestLogQueryDTO.setStartTime(queryTime.toString().split(" ")[0] + " 00:00:00");
        interfaceRequestLogQueryDTO.setMgroup(taskDO.getMgroup());
        interfaceRequestLogQueryDTO.setProjectName(taskDO.getProjectName());
        interfaceRequestLogQueryDTO.setIpAddress(ipAddress);
        return interfaceRequestLogQueryDTO;
    }
}
