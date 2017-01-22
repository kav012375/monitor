package com.wulin.web.controller.data;

import com.wulin.dal.ServerList.dao.ServerListDAO;
import com.wulin.dal.ServerList.entity.ServerListDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zeusw on 2016/8/31.
 */
@Controller
@RequestMapping(value = "/server",method = RequestMethod.POST)
public class ServerAction {
    @Autowired
    private ServerListDAO serverListDao;
    @ResponseBody
    @RequestMapping("/getallserverlist")
    public List<ServerListDO> GetAllServerList(){
        List<ServerListDO> result = null;
        result = serverListDao.getAllServerList();
        return result;
    }
}
