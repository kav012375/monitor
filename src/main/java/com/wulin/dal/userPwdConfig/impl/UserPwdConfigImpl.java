package com.wulin.dal.userPwdConfig.impl;

import com.wulin.dal.userPwdConfig.UserPwdConfigService;
import com.wulin.dal.userPwdConfig.dao.UserPwdConfigDAO;
import com.wulin.dal.userPwdConfig.entity.UserPwdConfigDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zeusw on 2016/12/28.
 */
public class UserPwdConfigImpl implements UserPwdConfigService {
    @Autowired
    UserPwdConfigDAO userPwdConfigDAO;

    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    public boolean normalInsertConfigInfoInToUserPwdConfigTable(UserPwdConfigDO userPwdConfigDO) {
        try {
            if (userPwdConfigDO.getAccount() == null){
                logger.error("Insert Into UserPwdConfig failed,because user account is null");
                return false;
            }
            int updateNum = userPwdConfigDAO.insertUserPwdConfig(userPwdConfigDO);
            if (updateNum<=0){
                logger.error("Insert Into UserPwdConfig failed,user account is"+userPwdConfigDO.getAccount());
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            logger.error("Insert Into UserPwdConfig failed,because exception is " +e.getMessage());
            return false;
        }
    }
}
