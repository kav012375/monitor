package com.wulin.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wulin.biz.common.dto.ActionDO;
import com.wulin.biz.common.dto.PositionDO;
import com.wulin.biz.common.dto.TaskDistributeDTO;
import com.wulin.biz.core.task.service.TaskService;
import com.wulin.dal.task.constants.StatusEnum;
import com.wulin.dal.task.dao.TaskDAO;
import com.wulin.dal.task.entity.TaskDO;
import com.wulin.dal.taskInstance.dao.TaskInstanceDAO;
import com.wulin.dal.taskInstance.entity.TaskInstanceDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ws.soap.addressing.server.annotation.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zeusw on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/task",method = RequestMethod.POST)
public class TaskAction {
    @Autowired
    TaskDAO taskDAO;
    @Autowired
    TaskInstanceDAO taskInstanceDAO;
    @Autowired
    TaskService taskService;

    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    @RequestMapping(value = "/deleteTask")
    public void deleteTask(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession
    ) throws Throwable{
        try {
            String taskId = httpServletRequest.getParameter("taskid");
            if (taskId == null){
                logger.error("传入的taskid为空，无法执行删除操作！");
                return;
            }
            TaskDO taskDO = new TaskDO();
            taskDO.setId(Long.parseLong(taskId));
            int deleteNum = taskDAO.deleteTaskByTaskId(taskDO);
            if (deleteNum<=0){
                logger.error("删除任务出错，任务ID="+taskId);
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().println("删除失败，请稍候再试");
                return;
            }
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print("删除成功");
        }catch (Exception e){
            logger.error("删除任务出错，任务ID，错误原因："+e.getMessage());
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().println("删除失败，错误原因"+e.getMessage());
            return;
        }
    }

    /**
     * 模拟器请求任务
     * @param httpServletRequest
     * @param httpServletResponse
     * @param httpSession
     * @throws Throwable
     */
    @RequestMapping(value = "/gettask")
    public void getTask(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession
    ) throws Throwable {
        /**
         * 获取普通的任务
         */
        taskService.getNormalTask(httpServletRequest,httpServletResponse,httpSession);
    }

    /**
     * 修改任务
     * @param httpServletRequest
     * @param httpServletResponse
     * @param httpSession
     * @throws Throwable
     */
    @RequestMapping(value = "/modifytask")
    public void modifyTask(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession
    ) throws Throwable{
        httpServletRequest.setCharacterEncoding("utf-8");
        Long taskId = Long.parseLong(httpServletRequest.getParameter("taskid"));
        String mgroup = httpServletRequest.getParameter("mgroup");
        String projectName = httpServletRequest.getParameter("projectname");
        int loopRunTimes = Integer.parseInt(httpServletRequest.getParameter("looptimes"));
        String actions = httpServletRequest.getParameter("actions");
        String positions = httpServletRequest.getParameter("positions");
        if (actions == null||actions.equals("")||positions==null||positions.equals("")){
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print("位置或动作为空！");
            return;
        }

        TaskDO taskDO = new TaskDO();
        taskDO.setId(taskId);
        taskDO.setLoopRunTimes(loopRunTimes);
        taskDO.setMgroup(mgroup);
        taskDO.setProjectName(projectName);
        //获取当前任务的content
        TaskDO oldTaskDO = taskDAO.findTaskById(taskId);

        TaskDistributeDTO taskDistributeDTO = new TaskDistributeDTO();
        String content = oldTaskDO.getTaskContent();
        JSONObject object = JSON.parseObject(content);

        taskDistributeDTO.setTaskUid(object.getString("taskUid"));
        taskDistributeDTO.setMediaName(object.getString("mediaName"));
        taskDistributeDTO.setStatus(object.getString("status"));
        taskDistributeDTO.setUsername(object.getString("username"));
        taskDistributeDTO.setPassword(object.getString("password"));

        //组装新的task_content
        List<PositionDO> positionDOs = new ArrayList<PositionDO>();
        List<ActionDO> actionDOs = new ArrayList<ActionDO>();
        String[] positionsValueList = positions.split(";");
        String[] actionsValueList = actions.split(";");
        for (String eve:positionsValueList) {
            PositionDO pDO = new PositionDO();
            pDO.setPosition(eve);
            positionDOs.add(pDO);
        }
        for (String eve:actionsValueList) {
            ActionDO aDO = new ActionDO();
            aDO.setAction(eve);
            actionDOs.add(aDO);
        }

        taskDistributeDTO.setActionDOs(actionDOs);
        taskDistributeDTO.setPositionDOs(positionDOs);
        String json = JSON.toJSONString(taskDistributeDTO);
        taskDO.setTaskContent(json);

        int updateNum = taskDAO.updateTaskById(taskDO);
        if(updateNum < 1){
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print("更新失败，请联系管理员！");
        }else{
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print("更新成功！");
        }

    }
}
