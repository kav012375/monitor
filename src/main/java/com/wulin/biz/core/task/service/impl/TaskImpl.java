package com.wulin.biz.core.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wulin.biz.core.task.service.TaskService;
import com.wulin.dal.task.constants.StatusEnum;
import com.wulin.dal.task.dao.TaskDAO;
import com.wulin.dal.task.entity.TaskDO;
import com.wulin.dal.taskInstance.dao.TaskInstanceDAO;
import com.wulin.dal.taskInstance.entity.TaskInstanceDO;
import com.wulin.dal.userPwdConfig.dao.UserPwdConfigDAO;
import com.wulin.dal.userPwdConfig.entity.UserPwdConfigDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zeusw on 2016/12/30.
 */
public class TaskImpl implements TaskService {
    @Autowired
    TaskDAO taskDAO;
    @Autowired
    TaskInstanceDAO taskInstanceDAO;
    @Autowired
    UserPwdConfigDAO userPwdConfigDAO;
    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");
    private static int oldIndex = 0;
    private Random random = new Random();
    public void getNormalTask(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                HttpSession httpSession) throws Throwable {
        try {
            TaskDO taskDO = new TaskDO();
            TaskInstanceDO taskInstanceDO = new TaskInstanceDO();
            httpServletRequest.setCharacterEncoding("utf-8");
            //获取请求
            String requestJson = httpServletRequest.getParameter("request");
            JSONObject jsonObject = JSON.parseObject(requestJson);
            String projectName = jsonObject.getString("projectName");
            String mgroup = jsonObject.getString("group");
            String ip = jsonObject.getString("ip");
            String accessToken = jsonObject.getString("password");
            String accountType = jsonObject.getString("type");
            if(projectName == null||mgroup==null||ip==null||accessToken==null){
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().println("参数不完整");
                return;
            }
            taskDO.setProjectName(projectName);
            taskDO.setMgroup(mgroup);
            taskDO.setStatus(0);
            //获取未完成的任务
            List<TaskDO> taskDOs = taskDAO.findVailedTaskByStatusAndGroupAndProjectAndRuntimes(taskDO);
            if(taskDOs.size() <1 ){
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().println("无任务可以领用");
                return;
            }
            /**
             * 随机下发逻辑
             */
            int iszie = taskDOs.size();
            int randomIndex = random.nextInt(iszie);
            logger.debug("current random index = "+randomIndex);
            taskDO = taskDOs.get(randomIndex);
            //获取到任务之后，将任务次数减1
            taskDO.setRunTimes(taskDO.getRunTimes()-1);
            if (taskDO.getRunTimes() == 0){
                taskDO.setStatus(StatusEnum.RunFinish.getCode());
            }
            /**
             * 逻辑结束
             */
            //更新任务次数和状态
            int taskUpdataNum = taskDAO.updateTaskById(taskDO);
            if(taskUpdataNum<1){
                System.out.println("更新task表失败");
                return;
            }
            taskInstanceDO.setTaskId(taskDO.getId());
            taskInstanceDO.setContent(taskDO.getTaskContent());
            taskInstanceDO.setGmtCreate(new java.sql.Timestamp(System.currentTimeMillis()));
            taskInstanceDO.setIp(ip);
            taskInstanceDAO.insertTaskInstance(taskInstanceDO);
            Long uid = taskInstanceDO.getUid();
            if(uid<=0){
                System.out.println("更新task_instance表失败");
//                return;
            }
            JSONObject object = JSON.parseObject(taskDO.getTaskContent());
            object.remove("taskUid");
            object.put("taskUid",uid.toString());
            taskInstanceDO.setContent(JSONObject.toJSONString(object));
            int taskInstanceUpdateNum = taskInstanceDAO.updateTaskInstanceByUid(taskInstanceDO);
            if(taskInstanceUpdateNum<=0){
                System.out.println("第二次更新task_instance表失败");
//                return;
            }
            String content = taskInstanceDO.getContent();
            if(accountType != null){
                //根据账户类型从账号库中随机选择一个账号密码
                UserPwdConfigDO userPwdConfigDO = userPwdConfigDAO.findRandomUserPwdByProject(accountType);
                if (userPwdConfigDO != null){
                    JSONObject tmpObj = JSON.parseObject(content);
                    if (userPwdConfigDO.getAccount() != null && userPwdConfigDO.getPwd()!=null){
                        tmpObj.remove("username");
                        tmpObj.remove("password");
                        tmpObj.put("username",userPwdConfigDO.getAccount());
                        tmpObj.put("password",userPwdConfigDO.getPwd());
                        content = JSONObject.toJSONString(tmpObj);
                    }
                }
            }
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().println(content);
        }catch (Exception e){
            e.printStackTrace();
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().println("系统异常，异常原因："+e.getMessage());
        }
    }
}
