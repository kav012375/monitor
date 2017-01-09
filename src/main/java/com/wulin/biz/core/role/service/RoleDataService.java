package com.wulin.biz.core.role.service;

import com.wulin.biz.common.dto.ProsessionInitDataDTO;
import com.wulin.dal.Role.entity.RoleDO;
import org.springframework.stereotype.Service;

/**
 * Created by zeusw on 2016/11/12.
 */
@Service
public interface RoleDataService {

    /**
     * @Date 2016/11/24 17:50
     * @Author guoyang.fgy
     * 获取随机中文名
     * @return
     */
    String getRandomName();

    /**
     * @Date  18:03
     * @Author guoyang.fgy
     * 新建角色方法，初始化角色数据的方法
     * @param prosessionInitDataDTO
     * @return
     */
    RoleDO createInitRoleData(ProsessionInitDataDTO prosessionInitDataDTO);

}
