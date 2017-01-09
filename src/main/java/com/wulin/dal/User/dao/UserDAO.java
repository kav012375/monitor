package com.wulin.dal.User.dao;

import com.wulin.dal.User.entity.UserDO;


/**
 * Created by FengG on 16/6/16.
 */
public interface UserDAO {
    UserDO findAll();
    int count();
    void InsertNewUser(UserDO userDO);
    UserDO FindUserInfoByUserAcct(String uacct);
}
