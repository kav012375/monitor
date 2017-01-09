package com.wulin.biz.timedTask;

import com.wulin.dal.scScheduleLog.dao.ScScheduleLogDAO;
import com.wulin.dal.scScheduleLog.entity.ScScheduleLogDO;
import com.wulin.dal.task.constants.StatusEnum;
import com.wulin.dal.task.dao.TaskDAO;
import com.wulin.dal.task.entity.TaskDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zeusw on 2016/12/26.
 */
@Service
public class RefreshTask {
    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");
    @Autowired
    TaskDAO taskDAO;
    @Autowired
    ScScheduleLogDAO scScheduleLogDAO;

    /**
     * 刷新每日任务，进行任务重置
     */
    public void refreshDailyLoopTask(){
        try {
            final Long startTime = System.currentTimeMillis();
            /**
             * 获取所有的已经运行完的，并且循环类型为日循环的任务
             */
            List<TaskDO> taskDOs = new ArrayList<TaskDO>();
            TaskDO queryTaskDO = new TaskDO();
            queryTaskDO.setLoopType(1);
//            queryTaskDO.setStatus(StatusEnum.RunFinish.getCode());
//            taskDOs = taskDAO.findTaskByLoopTypeAndStatus(queryTaskDO);
            taskDOs = taskDAO.findTaskByLoopType(queryTaskDO);
            if(taskDOs.size()<=0){
                logger.debug("No tasks need to be refreshed");
                return;
            }
            for (TaskDO t : taskDOs){
                logger.debug("开始更新数据,taskid = "+t.getId());
                t.setStatus(StatusEnum.NotRun.getCode());
                t.setRunTimes(t.getLoopRunTimes());
                int updateNum = taskDAO.updateTaskById(t);
                if (updateNum <=0){
                    logger.error("更新task数据表失败，失败的taskid = "+t.getId());
                }
                logger.debug("更新任务id为task"+t.getId()+"的任务成功");
            }
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
            logger.error("定时任务：refreshDailyLoopTask 执行失败，失败原因："+e.getMessage());
        }
    }

    /**
     * 将执行完的任务实例移动到历史表中
     */
    public void moveFinishTaskInstanceToHistoryTable(){
        final Long startTime = System.currentTimeMillis();
        final Long endTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            public void run() {
                String costTime = String.valueOf(endTime-startTime);
                ScScheduleLogDO scScheduleLogDO = new ScScheduleLogDO();
                scScheduleLogDO.setScheduleTime(new Timestamp(System.currentTimeMillis()));
                scScheduleLogDO.setTaskName("com.wulin.biz.timedTask.moveFinishTaskInstanceToHistoryTable");
                scScheduleLogDO.setCostTime(costTime);
                scScheduleLogDAO.insertScScheduleTaskLog(scScheduleLogDO);
            }
        }).start();
    }


}
