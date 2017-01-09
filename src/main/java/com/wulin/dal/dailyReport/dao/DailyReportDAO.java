package com.wulin.dal.dailyReport.dao;

import com.wulin.dal.dailyReport.dto.DailyRportQueryDTO;
import com.wulin.dal.dailyReport.entity.DailyReportDO;

import java.util.List;

/**
 * Created by zeusw on 2017/1/4.
 */
public interface DailyReportDAO {
    int insertDailyReport(DailyReportDO dailyReportDO);
    List<DailyReportDO> findDailyReportContentByTime(DailyRportQueryDTO DailyRportQueryDTO);
}
