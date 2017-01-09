package com.wulin.controller.data;

import com.wulin.biz.timedTask.RefreshTask;
import com.wulin.biz.timedTask.ReportTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zeusw on 2017/1/3.
 */
@Controller
@RequestMapping(value = "/timedtask",method = RequestMethod.POST)
public class TimedTaskAction {
    @Autowired
    RefreshTask refreshTask;
    @Autowired
    ReportTask reportTask;

    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    @RequestMapping(value = "/runtask")
    public void runTimedTaskByMan(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession
    ) throws Throwable{
        String method = httpServletRequest.getParameter("method");
        if (method == null || method.equals("")){
            logger.error("no task need to run!");
            return;
        }
        if(method.equals("moveFinishTaskInstanceToHistoryTable")){
            refreshTask.moveFinishTaskInstanceToHistoryTable();
        }
        if (method.equals("generateDailyReportTask")){
            reportTask.generateDailyReportTask();
        }
    }
}
