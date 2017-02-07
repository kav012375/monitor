package com.wulin.web.controller.data;

import com.wulin.dal.cfgArticlesType.dao.CfgArticlesTypeDAO;
import com.wulin.dal.cfgArticlesType.entity.CfgArticlesTypeDO;
import com.wulin.dal.infArticles.dao.InfArticlesDAO;
import com.wulin.dal.infArticles.entity.InfArticlesDO;
import com.wulin.utils.HttpServletResponseOutputUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zeusw on 2017/2/6.
 */
@Controller
@RequestMapping(value = "/article",method = RequestMethod.POST)
public class ArticleAction {
    @Autowired
    CfgArticlesTypeDAO cfgArticlesTypeDAO;
    @Autowired
    InfArticlesDAO infArticlesDAO;

    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    @RequestMapping(value = "/add_new_article_type")
    public void addNewArticleType(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws Throwable{
        String artName = httpServletRequest.getParameter("aType").toString();
        try {
            if (artName == null || artName.equals("")){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"评论类型名为空！");
                return;
            }
            CfgArticlesTypeDO cfgArticlesTypeDO = new CfgArticlesTypeDO();
            cfgArticlesTypeDO.setArticleTypeName(artName);
            int insertNum = cfgArticlesTypeDAO.insertNewArticleType(cfgArticlesTypeDO);
            if (insertNum<1){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"插入失败！");
                return;
            }else{
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"新增成功！");
            }
        }catch (Exception e){
            HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"系统异常！");
            logger.error("Add new article error, error is " + e.getMessage());
            return;
        }

    }

    @RequestMapping(value = "/del_article_type")
    public void delArticleTypeById(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws Throwable{
        String artName = httpServletRequest.getParameter("artName").toString();
        try {
            if (artName == null || artName.equals("")){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"评论类型Id为空！");
                return;
            }
            final CfgArticlesTypeDO cfgArticlesTypeDO = cfgArticlesTypeDAO.findArticleTypeByArticleTypeName(artName);
            int delNum = cfgArticlesTypeDAO.deleteArticleTypeByArticleTypeName(artName);
            new Thread(new Runnable() {
                public void run() {
                    infArticlesDAO.deleteArticlesByArticleTypeId(cfgArticlesTypeDO.getId());
                }
            }).start();
            if (delNum<1){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"删除失败！");
                return;
            }else{
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"删除成功！");
            }
        }catch (Exception e){
            HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"系统异常！");
            logger.error("delete article error, error is " + e.getMessage());
            return;
        }
    }

    @RequestMapping(value = "/modify_article_type")
    public void modifyArticleType(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws Throwable{
        String artId = httpServletRequest.getParameter("aId").toString();
        String artName = httpServletRequest.getParameter("aType").toString();
        try {
            if (artId == null || artId.equals("") || artName == null || artName.equals("")){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"参数错误，有空值！");
                return;
            }
            CfgArticlesTypeDO cfgArticlesTypeDO = new CfgArticlesTypeDO();
            cfgArticlesTypeDO.setId(Integer.parseInt(artId));
            cfgArticlesTypeDO.setArticleTypeName(artName);
            int updateNum = cfgArticlesTypeDAO.updateArticleTypeNameById(cfgArticlesTypeDO);
            if (updateNum<1){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"更新失败！");
                return;
            }else{
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"更新成功！");
            }
        }catch (Exception e){
            HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"系统异常！");
            logger.error("delete article error, error is " + e.getMessage());
            return;
        }
    }

    @RequestMapping(value = "/modify_articlescontent_by_id")
    public void modifyArticlesContentById(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) throws Throwable{
        String artId = httpServletRequest.getParameter("aId").toString();
        String artContent = httpServletRequest.getParameter("aContent").toString();
        try {
            if (artId == null || artId.equals("")){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"参数错误，有空值！");
                return;
            }
            InfArticlesDO infArticlesDO = new InfArticlesDO();
            infArticlesDO.setArticleContent(artContent);
            infArticlesDO.setId(Long.parseLong(artId));
            int updateNum = infArticlesDAO.updateArticlesContentById(infArticlesDO);
            if (updateNum<1){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"更新失败！");
                return;
            }else{
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"更新成功！");
            }
        }catch (Exception e){
            HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"系统异常！");
            logger.error("delete article error, error is " + e.getMessage());
            return;
        }
    }

    @RequestMapping(value = "/delete_articlescontent_by_id")
    public void deleteArticlesContentById(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) throws Throwable{
        //传入多个id时，使用;分隔
        String[] artIds = httpServletRequest.getParameter("aId").toString().split(";");
        String updateFailedArticlesId = "";
        try {
            if (artIds == null || artIds.length<1){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"参数错误，有空值！");
                return;
            }
            for( String artId : artIds){
                int updateNum = infArticlesDAO.deleteArticlesById(Long.parseLong(artId));
                if (updateNum<1) {
                    updateFailedArticlesId = updateFailedArticlesId + artId + ";";
                }
            }
            if(updateFailedArticlesId.length() >= 2){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"更新完成，失败的数据为：" + updateFailedArticlesId);
            }else{
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"更新完成");
            }
        }catch (Exception e){
            HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"系统异常！");
            logger.error("delete article error, error is " + e.getMessage());
            return;
        }
    }

    @RequestMapping(value = "/add_new_articles_content")
    public void addNewArticlesContet(HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Throwable{
        String artId = httpServletRequest.getParameter("aId").toString();
        String artContent = httpServletRequest.getParameter("aContent").toString();
        try {
            if (artId == null || artId.equals("")){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"参数错误，有空值！");
                return;
            }
            InfArticlesDO infArticlesDO = new InfArticlesDO();
            infArticlesDO.setArticleContent(artContent);
            infArticlesDO.setArticleTypeId(Integer.parseInt(artId));
            int updateNum = infArticlesDAO.insertNewArticlesContentByArticleTypeId(infArticlesDO);
            if (updateNum<1){
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"新增失败！");
                return;
            }else{
                HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"新增成功！");
            }
        }catch (Exception e){
            HttpServletResponseOutputUtils.printMsgToWeb(httpServletResponse,"系统异常！");
            logger.error("delete article error, error is " + e.getMessage());
            return;
        }
    }
}
