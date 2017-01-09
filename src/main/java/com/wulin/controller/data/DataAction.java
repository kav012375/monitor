package com.wulin.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wulin.biz.common.dto.ActionDO;
import com.wulin.biz.common.dto.PositionDO;
import com.wulin.biz.common.dto.TaskDistributeDTO;
import com.wulin.biz.common.service.CheckService;
import com.wulin.biz.core.role.service.RoleDataService;
import com.wulin.dal.task.constants.StatusEnum;
import com.wulin.dal.task.dao.TaskDAO;
import com.wulin.dal.task.entity.TaskDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeusw on 2016/11/12.
 */
@Controller
@RequestMapping(value = "/data",method = RequestMethod.POST)
public class DataAction {
    @Autowired
    CheckService checkService;
    @Autowired
    RoleDataService roleDataService;
    @Autowired
    TaskDAO taskDAO;
    private static Logger logger = LoggerFactory.getLogger("CONSOLE");
    @RequestMapping(value = "getname.do")
    public String getRandomName(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession){
        String name = roleDataService.getRandomName();
        try {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().println(name);
        }catch (Exception e){
            logger.error(e.toString());
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "addtask.do")
    public String addNewTask(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession
    ) throws Throwable{
        httpServletRequest.setCharacterEncoding("UTF-8");
        String mediaName =
                httpServletRequest.getParameter("mediaName") != null ? httpServletRequest.getParameter("mediaName") : "default";
        String username =
                httpServletRequest.getParameter("username") != null ? httpServletRequest.getParameter("username") : "default";
        String password =
                httpServletRequest.getParameter("password") != null ? httpServletRequest.getParameter("password") : "default";
        String positions =
                httpServletRequest.getParameter("positions") != null ? httpServletRequest.getParameter("positions") : "default";
        String actions =
                httpServletRequest.getParameter("actions") != null ? httpServletRequest.getParameter("actions") : "default";
        String runtimes =
                httpServletRequest.getParameter("runtimes") !=null ? httpServletRequest.getParameter("runtimes") : "default";
        String mgroup =
                httpServletRequest.getParameter("mgroup") !=null ? httpServletRequest.getParameter("mgroup") : "default";
        String projectName =
                httpServletRequest.getParameter("projectName") !=null ? httpServletRequest.getParameter("projectName") : "default";
        String loopType =
                httpServletRequest.getParameter("looptype") !=null ? httpServletRequest.getParameter("looptype") : "default";
        String loopRunTimes =
                httpServletRequest.getParameter("loopruntimes") !=null ? httpServletRequest.getParameter("loopruntimes") : "default";

        try {
            if(     mediaName.equals("default")  ||
                    username.equals("default") ||
                    password.equals("default") ||
                    positions.equals("default") ||
                    actions.equals("default")||
                    runtimes.equals("default")||
                    mgroup.equals("default")||
                    projectName.equals("default")||
                    loopType.equals("default") ){
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().println("参数为空4#1xzp7O");
                return null;
            }
            /**
             * 创建DTO开始拼装JSON
             */
            TaskDistributeDTO taskDistributeDTO = new TaskDistributeDTO();
            TaskDO taskDO = new TaskDO();
            taskDAO.insertTask(taskDO);
            Long id = taskDO.getId();
            if(id<=0L){
                logger.error("taskDAO.insertTask run failed");
                return null;
            }
            taskDistributeDTO.setStatus(String.valueOf(StatusEnum.NotRun.getCode()));
            taskDistributeDTO.setUsername(username);
            taskDistributeDTO.setPassword(password);
            taskDistributeDTO.setMediaName(mediaName);
            String[] positionsValueList = positions.split(";");
            String[] actionsValueList = actions.split(";");
            List<PositionDO> positionDOs = new ArrayList<PositionDO>();
            List<ActionDO> actionDOs = new ArrayList<ActionDO>();
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
            taskDistributeDTO.setPositionDOs(positionDOs);
            taskDistributeDTO.setActionDOs(actionDOs);
            taskDistributeDTO.setTaskUid(String.valueOf(id));
            String json = JSON.toJSONString(taskDistributeDTO);
            taskDO.setId(id);
            taskDO.setTaskContent(json);
            taskDO.setStatus(StatusEnum.NotRun.getCode());
            taskDO.setRunTimes(Integer.parseInt(runtimes));
            taskDO.setMgroup(mgroup);
            taskDO.setProjectName(projectName);
            if (loopType.equals("2")){
                taskDO.setLoopType(2);
            }else{
                taskDO.setLoopType(1);
                taskDO.setLoopRunTimes(Integer.parseInt(runtimes));
            }
            taskDAO.updateTaskById(taskDO);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().println("成功4#1xzp7O");
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "getAllTask.do")
    @ResponseBody
    public List<TaskDO> getAllTask(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession
    ) throws Throwable{
        List<TaskDO> taskDOs = new ArrayList<TaskDO>();
        try {
            taskDOs = taskDAO.findAllTasks();
            if (taskDOs == null || taskDOs.size() <= 0){
                return null;
            }
            return taskDOs;
        }catch (Exception e){
            logger.error("query all tasks failed ");
            return null;
        }
    }

    @RequestMapping(value = "/getTaskActionAndPosition.do")
    public void getTaskActionAndPosition(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws Throwable {
        try {
            Long taskId = Long.parseLong(httpServletRequest.getParameter("id"));
            TaskDO taskDO = taskDAO.findTaskById(taskId);
            if (taskDO == null){
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().print("任务不存在");
                return;
            }
            JSONObject object = JSON.parseObject(taskDO.getTaskContent());
            String result = "";
            JSONArray jsonArray = object.getJSONArray("actionDOs");
            for (Object s : jsonArray){
                JSONObject tmpObj = JSON.parseObject(s.toString());
                result = result+tmpObj.getString("action")+";";
            }
            jsonArray = object.getJSONArray("positionDOs");
            result = result + "&&&&";
            for (Object s : jsonArray){
                JSONObject tmpObj = JSON.parseObject(s.toString());
                result = result+tmpObj.getString("position")+";";
            }

            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print(result);

        }catch (Exception e){
            logger.error("获取位置和动作错误，错误原因 : "+e.getMessage());
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print("获取异常");
        }


    }
}
