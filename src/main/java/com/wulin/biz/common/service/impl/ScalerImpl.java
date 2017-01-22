package com.wulin.biz.common.service.impl;

import com.wulin.biz.common.service.ScalerService;
import com.wulin.dal.interfaceRequestLog.dao.InterfaceRequestLogDAO;
import com.wulin.dal.interfaceRequestLog.dto.InterfaceRequestLogQueryDTO;
import com.wulin.dal.interfaceRequestLog.entity.InterfaceRequestLogDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;


/**
 * Created by zeusw on 2017/1/11.
 */
public class ScalerImpl implements ScalerService {
    @Autowired
    InterfaceRequestLogDAO interfaceRequestLogDAO;

    public void scaleRequest(final InterfaceRequestLogDO interfaceRequestLogDO) {
        new Thread(new Runnable() {
            public void run() {
                interfaceRequestLogDAO.insertInterfaceRequestLog(interfaceRequestLogDO);
            }
        }).start();

    }

    public Long getRequestTimesByDay() {
        InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO = new InterfaceRequestLogQueryDTO();
        String timeBefore = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
        interfaceRequestLogQueryDTO.setStartTime(timeBefore+" 00:00:00");
        interfaceRequestLogQueryDTO.setEndTime(timeBefore+" 23:59:59");
        Long times = interfaceRequestLogDAO.countRequestTimesByDay(interfaceRequestLogQueryDTO);
        return times;
    }

    public void resetScalerTimes() {

    }

    public boolean checkDuplicateIp(InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO)
            throws Throwable {
        int resultNum = interfaceRequestLogDAO.checkDuplicateIp(interfaceRequestLogQueryDTO);
        if (resultNum>=1){
            return false;
        }else {
            return true;
        }

    }
}
