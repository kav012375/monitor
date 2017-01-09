package com.wulin.dal.taskInstance.dao;

import com.wulin.dal.dailyReport.dto.DailyRportQueryDTO;
import com.wulin.dal.taskInstance.entity.TaskInstanceDO;

import java.util.List;

/**
 * Created by zeusw on 2016/12/21.
 */
public interface TaskInstanceDAO {
    List<TaskInstanceDO> findTaskInstanceByUid(Long uid);
    Long insertTaskInstance(TaskInstanceDO taskInstanceDO);
    int updateTaskInstanceByUid(TaskInstanceDO taskInstanceDO);
    Long countTaskInstanceByTaskId(DailyRportQueryDTO dailyRportQueryDTO);
    List<Long> findTaskIdListByTimeStamp(DailyRportQueryDTO dailyRportQueryDTO);
    Long deleteTaskInstanceByTimeStamp(DailyRportQueryDTO dailyRportQueryDTO);
}
