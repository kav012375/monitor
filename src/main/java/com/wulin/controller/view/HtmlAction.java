package com.wulin.controller.view;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wulin.dal.Prosession.dao.ProsessionDAO;
import com.wulin.dal.Role.dao.RoleDAO;
import com.wulin.biz.core.user.service.UserService;
import com.wulin.dal.task.dao.TaskDAO;
import com.wulin.dal.task.entity.TaskDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeusw on 2016/10/23.
 */
@Controller
@RequestMapping(value = "/html")
public class HtmlAction {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private ProsessionDAO prosessionDAO;
    @Autowired
    private TaskDAO taskDAO;

    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    @RequestMapping(value = "index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "main")
    public ModelAndView MainPage(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle", "控制台");
        mav.setViewName("mainpage");
        return mav;
    }

    @RequestMapping(value = "normal_task_config")
    public ModelAndView NormalTaskConfig() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/taskconfig/normalTaskConfig");
        return mv;
    }

    @RequestMapping(value = "taskquery")
    public ModelAndView TaskQuery() {
        ModelAndView mv = new ModelAndView();
        List<TaskDO> taskDOList = new ArrayList<TaskDO>();
        try {
            taskDOList = taskDAO.findAllTasks();
            logger.info("task query success");
            mv.addObject("datasource", taskDOList);
            mv.setViewName("/monitor/taskquery");
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return mv;
        }
    }

    @RequestMapping(value = "login")
    public ModelAndView LoginPage(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/login");
        return mav;
    }

    @RequestMapping(value = "timedTaskConfig")
    public ModelAndView timedTaskConfig(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/taskconfig/TimedTaskConfig");
        return mav;
    }
//    @RequestMapping(value = "register")
//    public ModelAndView RegisterPage(
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse,
//            HttpSession httpSession){
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/user/register");
//        mav.addObject("gameTitle","热血魔兽");
//        mav.addObject("pageTitle","热血魔兽-注册");
//        return mav;
//    }


}
