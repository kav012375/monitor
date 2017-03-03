package com.wulin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zeus Feng on 2017/2/27.
 *
 * @author Zeus Feng
 * @date 2017/02/27
 */
public class DateTimeUtil {

    private static final Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    /**
     * 获取前n天的当前时间
     * @param dayPast 前n天，例如前一天的当前时间则填写-1
     * @return
     */
    public static String getTimeBySomeTimeAgo(int dayPast){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,dayPast);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime=formatter.format(c.getTime());
        //String strStart=mDateTime.substring(0, 16);
        return mDateTime;
    }

}
