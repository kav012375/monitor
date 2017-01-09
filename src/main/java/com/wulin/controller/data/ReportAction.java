package com.wulin.controller.data;

import com.alibaba.fastjson.JSON;
import com.wulin.biz.common.constants.ErrorCodeEnum;
import com.wulin.biz.common.dto.DailyReportPageDTO;
import com.wulin.dal.dailyReport.dao.DailyReportDAO;
import com.wulin.dal.dailyReport.dto.DailyReportContentDTO;
import com.wulin.dal.dailyReport.dto.DailyRportQueryDTO;
import com.wulin.dal.dailyReport.entity.DailyReportDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zeusw on 2017/1/5.
 */
@Controller
@RequestMapping(value = "/report")
public class ReportAction {
    @Autowired
    DailyReportDAO dailyReportDAO;

    @RequestMapping(value = "/frame")
    public ModelAndView getReportFrame(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/report/reportFrame");
        return mv;
    }

    @RequestMapping(value = "/daily")
    public ModelAndView getDailyReport(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        ModelAndView mv = new ModelAndView();
        DailyRportQueryDTO dailyRportQueryDTO = new DailyRportQueryDTO();
        String datatime = httpServletRequest.getParameter("reportTime");
        if (datatime == null){
            mv.addObject("errormsg",ErrorCodeEnum.NO_DAILY_REPROT.getDescription());
            return mv;
        }
        dailyRportQueryDTO.setStartTime(datatime+" 00:00:00");
        dailyRportQueryDTO.setEndTime(datatime+" 23:59:59");
        mv.setViewName("/report/dailyreport");
        List<DailyReportDO> dailyReportDOs = dailyReportDAO.findDailyReportContentByTime(dailyRportQueryDTO);
        if (dailyReportDOs == null){
            mv.addObject("errormsg",ErrorCodeEnum.NO_DAILY_REPROT.getDescription());
        }else {
            mv.addObject("dataresource",dailyReportDOs);
        }
        return mv;
    }
}
