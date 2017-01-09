package com.wulin.biz.core.user.service;

import com.wulin.dal.User.entity.UserDO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by FengG on 16/6/20.
 */
@Service
public interface UserService {
    /**
     * @Date 2016/11/17 10:30
     * @Author guoyang.fgy
     * 用户注册方法
     * @param userDO
     * @return
     */
    String insertUser(UserDO userDO);

    /**
     * @Date 2016/11/17 10:30
     * @Author guoyang.fgy
     * 用户登陆方法
     * @param userDO
     * @return
     */
    String userLogin(UserDO userDO);
    UserDO getAllUser();
    String saveUserLoginInfo();

    /**
     * @Date 2016/11/17 10:31
     * @Author guoyang.fgy
     * 查询用户是否登陆
     * @param httpSession 当前的请求session
     * @return 已经登陆返回true
     */
    boolean checkUserLoginStatus(HttpSession httpSession);
}
