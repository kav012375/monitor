package com.wulin.biz.core.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wulin.biz.common.service.ScalerService;
import com.wulin.biz.common.utils.ScalerUtils;
import com.wulin.biz.core.task.service.TaskService;
import com.wulin.dal.cfgArticlesType.dao.CfgArticlesTypeDAO;
import com.wulin.dal.infArticles.dao.InfArticlesDAO;
import com.wulin.dal.infArticles.entity.InfArticlesDO;
import com.wulin.dal.interfaceRequestLog.dao.InterfaceRequestLogDAO;
import com.wulin.dal.interfaceRequestLog.dto.InterfaceRequestLogQueryDTO;
import com.wulin.dal.interfaceRequestLog.entity.InterfaceRequestLogDO;
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
import java.sql.Timestamp;
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
    @Autowired
    ScalerService scalerService;
    @Autowired
    InterfaceRequestLogDAO interfaceRequestLogDAO;
    @Autowired
    CfgArticlesTypeDAO cfgArticlesTypeDAO;
    @Autowired
    InfArticlesDAO infArticlesDAO;

    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");
    private Random random = new Random();

    public void getNormalTask(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              HttpSession httpSession,
                              final String ipAddress) throws Throwable {
        TaskDO taskDO = new TaskDO();
        TaskInstanceDO taskInstanceDO = new TaskInstanceDO();
        List<TaskDO> taskDOs = new ArrayList<TaskDO>();
        try {
            httpServletRequest.setCharacterEncoding("utf-8");
            //获取请求
            String requestJson = httpServletRequest.getParameter("request");
            JSONObject jsonObject = JSON.parseObject(requestJson);
            String projectName = jsonObject.getString("projectName");
            String mgroup = jsonObject.getString("group");
            String ip = jsonObject.getString("ip");
            String accessToken = jsonObject.getString("password");
            String accountType = jsonObject.getString("type");
            if (projectName == null || mgroup == null || ip == null || accessToken == null) {
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().println("系统异常，参数不完整");
                return;
            }
            taskDO.setProjectName(projectName);
            taskDO.setMgroup(mgroup);
            taskDO.setStatus(0);

            //获取未完成的任务
            taskDOs = taskDAO.findVailedTaskByStatusAndGroupAndProjectAndRuntimes(taskDO);
            if (taskDOs.size() < 1) {
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().println("系统异常，无任务可以领用");
                return;
            }
            /**
             * 随机下发逻辑
             */
            int iszie = taskDOs.size();
            int randomIndex = random.nextInt(iszie);
            logger.debug("current random index = " + randomIndex);
            taskDO = taskDOs.get(randomIndex);
            //判断该任务是否需要过滤ip
            if(taskDO.isIpFilter()){
                //检查请求的ip是否重复
                InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO =
                        ScalerUtils.assembleInterfaceRequestLogQyueryUtil(taskDO,ipAddress);
                boolean checResult = scalerService.checkDuplicateIp(interfaceRequestLogQueryDTO);
                if (!checResult) {
                    //发现重复IP过滤掉
                    httpServletResponse.setCharacterEncoding("utf-8");
                    httpServletResponse.getWriter().println("重复的请求IP，被过滤");
                    return;
                }
            }
            //获取到任务并且ip检查通过之后，将任务次数减1
            taskDO.setRunTimes(taskDO.getRunTimes() - 1);
            if (taskDO.getRunTimes() == 0) {
                taskDO.setStatus(StatusEnum.RunFinish.getCode());
            }
            /**
             * 逻辑结束
             */
            //更新任务次数和状态
            int taskUpdataNum = taskDAO.updateTaskById(taskDO);
            if (taskUpdataNum < 1) {
                System.out.println("更新task表失败");
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().println("系统异常");
                return;
            }
            taskInstanceDO.setTaskId(taskDO.getId());
            taskInstanceDO.setContent(taskDO.getTaskContent());
            taskInstanceDO.setGmtCreate(new java.sql.Timestamp(System.currentTimeMillis()));
            taskInstanceDO.setIp(ip);
            taskInstanceDAO.insertTaskInstance(taskInstanceDO);
            Long uid = taskInstanceDO.getUid();
            if (uid <= 0) {
                System.out.println("更新task_instance表失败");
//                return;
            }
            JSONObject object = JSON.parseObject(taskDO.getTaskContent());
            object.remove("taskUid");
            object.put("taskUid", uid.toString());
            taskInstanceDO.setContent(JSONObject.toJSONString(object));
            int taskInstanceUpdateNum = taskInstanceDAO.updateTaskInstanceByUid(taskInstanceDO);
            if (taskInstanceUpdateNum <= 0) {
                System.out.println("第二次更新task_instance表失败");
//                return;
            }
            String content = taskInstanceDO.getContent();
            //账号类型不为空，则获取账号信息
            if (accountType != null) {
                //根据账户类型从账号库中随机选择一个账号密码
                UserPwdConfigDO userPwdConfigDO = userPwdConfigDAO.findRandomUserPwdByProject(accountType);
                if (userPwdConfigDO != null) {
                    JSONObject tmpObj = JSON.parseObject(content);
                    if (userPwdConfigDO.getAccount() != null && userPwdConfigDO.getPwd() != null) {
                        tmpObj.remove("username");
                        tmpObj.remove("password");
                        tmpObj.put("username", userPwdConfigDO.getAccount());
                        tmpObj.put("password", userPwdConfigDO.getPwd());
                        content = JSONObject.toJSONString(tmpObj);
                    }
                }
            }
            //评论类型不为空，则下发评论
            if((!taskDO.getArticleType().equals(""))&&(!taskDO.getArticleType().equals("null"))&&(taskDO.getArticleType()!=null)){
                //获取相关评论
                List<InfArticlesDO> infArticlesDOs = infArticlesDAO.findArticlesContentByArticleTypeId(
                        Integer.parseInt(taskDO.getArticleType()));
                int infSize = infArticlesDOs.size();
                if (infSize>=1){
                    int randomInf = random.nextInt(infSize);
                    String articlesContent = infArticlesDOs.get(randomInf).getArticleContent();
                    JSONObject infObj = JSON.parseObject(content);
                    infObj.put("articles",articlesContent);
                    content = JSONObject.toJSONString(infObj);
                }
            }
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().println(content);
            //记录请求日志
            InterfaceRequestLogDO interfaceRequestLogDO = new InterfaceRequestLogDO();
            interfaceRequestLogDO.setInterfaceName("getTask");
            interfaceRequestLogDO.setRequestTime(new Timestamp(System.currentTimeMillis()));
            interfaceRequestLogDO.setIpAddress(ipAddress);
            interfaceRequestLogDO.setProjectName(taskDO.getProjectName());
            interfaceRequestLogDO.setMgroup(taskDO.getMgroup());
            scalerService.scaleRequest(interfaceRequestLogDO);

        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().println("系统异常，异常原因：" + e.getMessage());
        }finally {
            //销毁大数据
            taskDOs.clear();
            taskDOs = null;
        }
    }
}
