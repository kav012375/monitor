package com.wulin.controller.data;

import com.wulin.biz.core.dataImportTools.UserPwdConfigDataImportService;
import com.wulin.dal.userPwdConfig.entity.UserPwdConfigDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeusw on 2016/12/29.
 */
@Controller
@RequestMapping(value = "/file")
public class FileAction {
    @Autowired
    UserPwdConfigDataImportService userPwdConfigDataImportService;
    private static Logger logger = LoggerFactory.getLogger("DEFAULT-APPENDER");

    @RequestMapping(value = "/upload")
    public void importUserPwdAction(
            @RequestParam("file") CommonsMultipartFile[] files,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            HttpSession httpSession) throws Throwable {
        String accountProject = httpServletRequest.getParameter("project");
        Long batchId =System.currentTimeMillis();
        if(accountProject == null){
            logger.error("project参数未传入，导入失败！");
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().print("project为空");
            return;
        }
        List<UserPwdConfigDO> userPwdConfigDOs = new ArrayList<UserPwdConfigDO>();
        Long begainTime = System.currentTimeMillis();
        for (int num = 0; num < files.length; num++) {
            logger.debug("开始进行文件导入，文件序列：" + num);
            try {
                if (!files[num].isEmpty()) {
                    /**
                     * 拿到上传的输入流
                     */
                    //FileInputStream fileInputStream = (FileInputStream) ;
                    InputStreamReader inputStreamReader = new InputStreamReader(files[num].getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String readStr = "";
                    logger.debug("数据导入开始....");
                    Long fileIndex = 0L;
                    while ((readStr = bufferedReader.readLine()) != null) {
                        if (readStr == "\n" || readStr == "" || readStr.equals("") || readStr.equals("\n") ){
                            continue;
                        }
                        UserPwdConfigDO userPwdConfigDO = new UserPwdConfigDO();
                        userPwdConfigDO.setAccount(readStr.split("-")[0]);
                        userPwdConfigDO.setPwd(readStr.split("-")[1]);
                        userPwdConfigDO.setBatchId(batchId);
                        userPwdConfigDO.setProject(accountProject);
                        userPwdConfigDataImportService.normalImportOneRecord(userPwdConfigDO);
                        fileIndex = fileIndex + 1L;
                        logger.debug("本次数据导入总计："+fileIndex.toString() + " 条");
                        //userPwdConfigDOs.add(userPwdConfigDO);
                    }
                    //userPwdConfigDataImportService.normalImport(userPwdConfigDOs);
                    httpServletResponse.setCharacterEncoding("utf-8");
                    httpServletResponse.getWriter().print("数据导入成功！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("导入文件出现异常，异常原因为：" + e.getMessage());
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().print("null");
            }

        }
        Long endTime = System.currentTimeMillis();
        logger.debug("本次导入总计耗时：" + (endTime - begainTime));
    }
}
