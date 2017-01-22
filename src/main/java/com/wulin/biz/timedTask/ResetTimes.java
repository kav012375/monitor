package com.wulin.biz.timedTask;

import com.wulin.biz.common.service.ScalerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zeusw on 2017/1/11.
 */
public class ResetTimes {
    @Autowired
    ScalerService scalerService;
    public void ResetReqTimes(){
        scalerService.resetScalerTimes();
    }
}
