package com.wulin.dal.infArticles.dao;

import com.wulin.dal.infArticles.entity.InfArticlesDO;

import java.util.List;

/**
 * Created by zeusw on 2017/2/6.
 */
public interface InfArticlesDAO {

    List<InfArticlesDO> findArticlesContentByArticleTypeId(int articleTypeId);
    int updateArticlesContentById(InfArticlesDO infArticlesDO);
    int deleteArticlesById(Long id);
    int deleteArticlesByArticleTypeId(int articleTypeId);
    int insertNewArticlesContentByArticleTypeId(InfArticlesDO infArticlesDO);

}
