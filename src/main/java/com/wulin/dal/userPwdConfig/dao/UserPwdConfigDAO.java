package com.wulin.dal.userPwdConfig.dao;

import com.wulin.dal.userPwdConfig.entity.UserPwdConfigDO;

/**
 * Created by zeusw on 2016/12/28.
 */
public interface UserPwdConfigDAO {
    int insertUserPwdConfig(UserPwdConfigDO userPwdConfigDO);
    UserPwdConfigDO findRandomUserPwdByProject(String type);
}
