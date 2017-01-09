package com.wulin.biz.core.task.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zeusw on 2016/12/30.
 */
public interface TaskService {
    /**
     * 获取普通的任务
     * @param httpServletRequest
     * @param httpServletResponse
     * @param httpSession
     * @return
     */
    void getNormalTask(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       HttpSession httpSession) throws Throwable;
}
