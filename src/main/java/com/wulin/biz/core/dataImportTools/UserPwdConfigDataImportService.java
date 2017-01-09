package com.wulin.biz.core.dataImportTools;


import com.wulin.dal.userPwdConfig.entity.UserPwdConfigDO;

import java.util.List;

/**
 * Created by zeusw on 2016/12/28.
 */
public interface UserPwdConfigDataImportService {
    void normalImport(List<UserPwdConfigDO> userPwdConfigDOList);
    void normalImportOneRecord(UserPwdConfigDO userPwdConfigDO);
}
