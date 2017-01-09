package com.wulin.biz.core.user.service.impl;

import com.wulin.dal.User.dao.UserDAO;
import com.wulin.dal.User.entity.UserDO;
import com.wulin.biz.core.user.constants.UserReturnCodeEnum;
import com.wulin.biz.common.constants.ErrorCodeEnum;
import com.wulin.biz.common.service.CheckService;
import com.wulin.biz.common.service.SecurityService;
import com.wulin.biz.core.user.service.UserService;
import com.wulin.biz.common.constants.SessionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * Created by FengG on 16/6/20.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private CheckService checkHelper;
    @Autowired
    private SecurityService securityService;
    private static final Logger logger = LoggerFactory.getLogger("CONSOLE");
    @Transactional
    public String insertUser(UserDO userDO) {
        /*注册新用户服务*/
        if (!paramCheck(userDO).equals(ErrorCodeEnum.PARAMETER_CHECK_PASS.getIndex().toString())){
            return paramCheck(userDO);
        }
        //正式的注册流程
        try{
            userDO.setUSER_PWD(securityService.Md5Creator(userDO.getUSER_PWD()));
            userDao.InsertNewUser(userDO);
            return UserReturnCodeEnum.USER_INSERT_SUCCESS.getIndex().toString();
        }catch (Exception e){
            //获取异常原因
            String error_res = e.getMessage().toString();
            System.err.println(error_res);
            //如果是主键冲突,则代表用户名已经存在,否则就是系统异常
            if(error_res.contains("USER_ACCT_UNIQUE")){
                return UserReturnCodeEnum.USER_HAS_ALREADY_EXIST.getIndex().toString();
            }else {
                return ErrorCodeEnum.SYSTEM_ERROR.getIndex().toString();
            }
        }
    }
    @Transactional
    public String userLogin(UserDO userDO) {
        UserDO getUserFromDatabase = new UserDO();
        if (!paramCheck(userDO).equals(ErrorCodeEnum.PARAMETER_CHECK_PASS.getIndex().toString())){
            return paramCheck(userDO);
        }
        //从数据库中取出用户
        try {
            getUserFromDatabase = userDao.FindUserInfoByUserAcct(userDO.getUSER_ACCT());
        }catch (Exception e){
            logger.error(e.toString());
            e.printStackTrace();
            System.err.println(ErrorCodeEnum.SYSTEM_ERROR.getDescription().toString());
            return ErrorCodeEnum.SYSTEM_ERROR.getIndex().toString();
        }
        //判空,如果为空,则代表用户名不存在
        if(getUserFromDatabase==null){
            return UserReturnCodeEnum.USER_DOES_NOT_EXIST.getIndex().toString();
        }
        //密码加密
        userDO.setUSER_PWD(securityService.Md5Creator(userDO.getUSER_PWD()));
        //密码比对
        if (userDO.getUSER_PWD().equals(getUserFromDatabase.getUSER_PWD())){
            System.out.print(userDO.getUSER_ACCT().toString() + "  登陆成功");
            return UserReturnCodeEnum.USER_LOGIN_SUCCESS.getIndex().toString();
        }
        else{
            return UserReturnCodeEnum.USER_PWD_IS_WRONG.getIndex().toString();
        }
    }
    public UserDO getAllUser() {
        return userDao.findAll();
    }
    @Transactional
    public String saveUserLoginInfo() {
        return null;
    }
    private String paramCheck(UserDO userDO){
        //检查用户名是否为空
        if (userDO.getUSER_ACCT() == null){
            return ErrorCodeEnum.USER_ACCT_CANNOT_BE_NULL.getIndex().toString();
        }
        //检查密码是否空
        if(userDO.getUSER_PWD() == null){
            return ErrorCodeEnum.USER_PWD_CANNOT_BE_NULL.getIndex().toString();
        }
        //检查用户名是否包含特殊字符
        if(checkHelper.IsCheckSpecialCharactors(userDO.getUSER_ACCT())){
            return ErrorCodeEnum.USER_ACCT_CANNOT_HAVE_SPECIAL_CHARACTORS.getIndex().toString();
        }
        //检查用户名是否包含中文字符
        if (checkHelper.IsCheckChineseCharactors(userDO.getUSER_ACCT())){
            return ErrorCodeEnum.USER_ACCT_CANNOT_HAVE_CHINESE_CHARACTORS.getIndex().toString();
        }
        //检查用户名的长度是否合法
        if(userDO.getUSER_ACCT().toString().length()<8||userDO.getUSER_ACCT().toString().length()>24){
            return ErrorCodeEnum.USER_ACCT_LENGTH_IS_TOO_LONG_OR_SHORT.getIndex().toString();
        }
        //检查用户密码长度是否合法
        if(userDO.getUSER_PWD().toString().length()<8||userDO.getUSER_PWD().toString().length()>32){
            return ErrorCodeEnum.USER_PWD_LENGTH_IS_TOO_LONG_OR_SHORT.getIndex().toString();
        }
        return ErrorCodeEnum.PARAMETER_CHECK_PASS.getIndex().toString();
    }
    @Transactional
    public boolean checkUserLoginStatus(HttpSession httpSession) {
        if(httpSession.getAttribute(SessionEnum.CurrentLoginUserAcct.getEnumKey()) == null){
            return false;
        }
        if(httpSession.getAttribute(SessionEnum.CurrentLoginTime.getEnumKey()) == null){
            return false;
        }
        if (httpSession.getAttribute(SessionEnum.CurrentAccessToken.getEnumKey()) == null){
            return false;
        }
        return true;
    }
}
