package com.wulin.dal.cfgArticlesType.dao;

import com.wulin.dal.cfgArticlesType.entity.CfgArticlesTypeDO;

import java.util.List;

/**
 * Created by zeusw on 2017/2/6.
 */
public interface CfgArticlesTypeDAO {
    List<CfgArticlesTypeDO> findAllCfgArticlesType();
    CfgArticlesTypeDO findArticleTypeById(int id);
    CfgArticlesTypeDO findArticleTypeByArticleTypeName(String articleTypeName);
    int insertNewArticleType(CfgArticlesTypeDO cfgArticlesTypeDO);
    int deleteArticleTypeById(int id);
    int deleteArticleTypeByArticleTypeName(String articleTypeName);
    int updateArticleTypeNameById(CfgArticlesTypeDO cfgArticlesTypeDO);
}
