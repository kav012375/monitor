package com.wulin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fengguoyang on 17/1/30.
 */
public class HttpServletResponseOutputUtils {

    private static final Logger logger = LoggerFactory.getLogger("LOGGER");

    /**
     * 调用getWriter().print()方法向网页输出信息
     * @param httpServletResponse
     * @param message
     */
    public static void printMsgToWeb(HttpServletResponse httpServletResponse,String message){
        try{
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print(message);
        }catch (Exception e){
            logger.error("向httpServletResponse输出内容失败，失败的原因为"+e.getMessage());
        }

    }
}
