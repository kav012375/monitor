package com.wulin.dal.ServerList.dao;

import com.wulin.dal.ServerList.entity.ServerListDO;

import java.util.List;

/**
 * Created by zeusw on 2016/8/31.
 */
public interface ServerListDAO {
    List<ServerListDO> getAllServerList();
}
