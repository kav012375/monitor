package com.wulin.biz.timedTask;

import com.wulin.dal.interfaceRequestLog.dao.InterfaceRequestLogDAO;
import com.wulin.dal.scScheduleLog.dao.ScScheduleLogDAO;
import com.wulin.dal.scScheduleLog.entity.ScScheduleLogDO;
import com.wulin.utils.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by Zeus Feng on 2017/2/27.
 *
 * @author Zeus Feng
 * @date 2017/02/27
 */
@Service
public class DataCleanTask {
    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    @Autowired
    InterfaceRequestLogDAO interfaceRequestLogDAO;
    @Autowired
    ScScheduleLogDAO scScheduleLogDAO;
    /**
     * 删除3天以前的请求日志
     */
    public void deleteInterfaceLog(){
        try {
            final Long startTime = System.currentTimeMillis();
            //获取前二天的时间
            String dayTime = DateTimeUtil.getTimeBySomeTimeAgo(-2);
            interfaceRequestLogDAO.deleteInterfaceLogByDateTime(dayTime);
            final Long endTime = System.currentTimeMillis();
            /**
             * 启动写日志任务
             */
            new Thread(new Runnable() {
                public void run() {
                    String costTime = String.valueOf(endTime-startTime);
                    ScScheduleLogDO scScheduleLogDO = new ScScheduleLogDO();
                    scScheduleLogDO.setScheduleTime(new Timestamp(System.currentTimeMillis()));
                    scScheduleLogDO.setTaskName("com.wulin.biz.timedTask.refreshDailyLoopTask");
                    scScheduleLogDO.setCostTime(costTime);
                    scScheduleLogDAO.insertScScheduleTaskLog(scScheduleLogDO);
                }
            }).start();
        }catch (Exception e){
            logger.error("删除接口请求数据失败，失败原因为："+e.getMessage());
        }
    }
}
