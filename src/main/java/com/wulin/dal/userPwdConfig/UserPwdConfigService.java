package com.wulin.dal.userPwdConfig;

import com.wulin.dal.userPwdConfig.entity.UserPwdConfigDO;

/**
 * Created by zeusw on 2016/12/28.
 */
public interface UserPwdConfigService {
    /**
     * 插入一条数据到用户名密码配置表
     * @param userPwdConfigDO
     * @return
     */
    boolean normalInsertConfigInfoInToUserPwdConfigTable(UserPwdConfigDO userPwdConfigDO);
}
