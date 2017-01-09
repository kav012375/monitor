package com.wulin.biz.core.dataImportTools.impl;

import com.wulin.biz.core.dataImportTools.UserPwdConfigDataImportService;
import com.wulin.dal.userPwdConfig.UserPwdConfigService;
import com.wulin.dal.userPwdConfig.entity.UserPwdConfigDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zeusw on 2016/12/28.
 */
public class UserPwdConfigDataImportImpl implements UserPwdConfigDataImportService {
    @Autowired
    UserPwdConfigService userPwdConfigService;

    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    public void normalImport(List<UserPwdConfigDO> userPwdConfigDOList) {
        if(userPwdConfigDOList.size()>0){
            for (UserPwdConfigDO userPwdConfigDO:userPwdConfigDOList) {
                boolean result =
                        userPwdConfigService.normalInsertConfigInfoInToUserPwdConfigTable(userPwdConfigDO);
            }
        }
    }

    public void normalImportOneRecord(UserPwdConfigDO userPwdConfigDO) {
        userPwdConfigService.normalInsertConfigInfoInToUserPwdConfigTable(userPwdConfigDO);
    }
}
