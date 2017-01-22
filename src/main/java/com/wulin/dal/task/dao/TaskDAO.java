package com.wulin.dal.task.dao;

import com.wulin.dal.task.entity.TaskDO;

import java.util.List;

/**
 * Created by zeusw on 2016/12/20.
 */
public interface TaskDAO {
    List<TaskDO> findTaskByStatusAndGroupAndProjectName(TaskDO taskDO);
    TaskDO findTaskById(Long id);
    Long insertTask(TaskDO taskDO);
    int updateTaskById(TaskDO taskDO);
    int updateTaskByLoopType(TaskDO taskDO);
    List<TaskDO> findVailedTaskByStatusAndGroupAndProjectAndRuntimes(TaskDO taskDO);
    List<TaskDO> findAllTasks();
    List<TaskDO> findTaskByLoopTypeAndStatus(TaskDO taskDO);
    List<TaskDO> findTaskByLoopType(TaskDO taskDO);
    int deleteTaskByTaskId(TaskDO taskDO);
    Long countRuningTaskAmount();
}
