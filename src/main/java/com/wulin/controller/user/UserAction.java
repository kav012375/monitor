package com.wulin.controller.user;

import com.wulin.dal.User.entity.UserDO;
import com.wulin.biz.core.user.constants.UserReturnCodeEnum;
import com.wulin.biz.common.constants.ErrorCodeEnum;
import com.wulin.biz.common.service.SecurityService;
import com.wulin.biz.core.user.service.UserService;
import com.wulin.biz.common.constants.SessionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by FengG on 16/6/20.
 */
@Controller
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserAction {
    //@Autowired
    //private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    //返回JSON
    @ResponseBody
    @RequestMapping("/get")
    public UserDO getUserInfo(){
        UserDO userDO = userService.getAllUser();
        return userDO;
    }

    @RequestMapping("/register.do")
    @ResponseBody
    public String regUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        UserDO userDO = new UserDO();
        String validataCode = "null";
        HttpSession httpSession  = httpServletRequest.getSession();
        userDO.setUSER_ACCT(httpServletRequest.getParameter("USER_ACCT"));
        userDO.setUSER_PWD(httpServletRequest.getParameter("USER_PWD"));
        userDO.setREG_IP(securityService.GetRealIpAddr(httpServletRequest));
        validataCode = httpServletRequest.getParameter("USER_CCODE").toString().toLowerCase();
        try {
            //判断是否为影子测试标记
            if (validataCode.equals("_t__shadow")){
                return userService.insertUser(userDO);
            }
            //判断验证码是否正确
            if((validataCode.compareTo(httpSession.getAttribute("validateCode").toString().toLowerCase())!=0)){
                return UserReturnCodeEnum.USER_CCODE_IS_NULL_OR_INCORRECT.getIndex().toString();
            }
            //判断用户IP地址是否可疑？
//            if(userDO.getREG_IP() == "127.0.0.1" || userDO.getREG_IP() == "localhost"){
//                return UserReturnCodeEnum.USER_IP_IS_BAD.getDescription();
//            }
            return userService.insertUser(userDO);
        }catch (Exception e){
            e.printStackTrace();
            return ErrorCodeEnum.SYSTEM_ERROR.getIndex().toString();
        }
    }
    @RequestMapping("/login.do")
    @ResponseBody
    public String loginUser(HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse,
                            HttpSession httpSession)
        throws IOException {
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT(httpServletRequest.getParameter("USER_ACCT"));
        userDO.setUSER_PWD(httpServletRequest.getParameter("USER_PWD"));
        try {
            String result = userService.userLogin(userDO);
            if(result.equals(UserReturnCodeEnum.USER_LOGIN_SUCCESS.getIndex().toString())){
                //用户登录成功
                String userLastLoginTime = (new Date()).toString();
                String accessToken = securityService.Md5Creator(userDO.getUSER_ACCT()+userLastLoginTime);
                httpSession.setAttribute(SessionEnum.CurrentLoginUserAcct.getEnumKey(),userDO.getUSER_ACCT());
                httpSession.setAttribute(SessionEnum.CurrentLoginTime.getEnumKey(),userLastLoginTime);
                httpSession.setAttribute(SessionEnum.CurrentAccessToken.getEnumKey(), accessToken);
                return result;
            }else
            {
                //用户登录失败
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ErrorCodeEnum.SYSTEM_ERROR.getDescription();
        }
    }
    @RequestMapping("/checklogin.do")
    @ResponseBody
    public String CheckLoginStatus(HttpServletRequest httpServletRequest,HttpSession httpSession)
        throws IOException{
        try {
            if(httpSession.getAttribute(SessionEnum.CurrentLoginUserAcct.getEnumKey()) == null){
                return UserReturnCodeEnum.USER_HAS_NOT_LOGIN.getIndex().toString();
            }else {
                return UserReturnCodeEnum.USER_HAS_ALREADY_LOGIN.getIndex().toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ErrorCodeEnum.SYSTEM_ERROR.getIndex().toString();
        }
    }
}
