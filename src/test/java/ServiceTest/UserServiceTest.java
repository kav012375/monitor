package ServiceTest;

import com.wulin.biz.timedTask.RefreshTask;
import com.wulin.biz.timedTask.ReportTask;
import com.wulin.dal.Prosession.dao.ProsessionDAO;
import com.wulin.dal.Role.dao.RoleDAO;
import com.wulin.dal.Prosession.entity.ProsessionDO;
import com.wulin.dal.User.entity.UserDO;
import com.wulin.biz.core.user.constants.UserReturnCodeEnum;
import com.wulin.biz.common.constants.ErrorCodeEnum;
import com.wulin.biz.common.service.CheckService;
import com.wulin.biz.common.service.SecurityService;
import com.wulin.biz.core.user.service.UserService;
import com.wulin.dal.task.dao.TaskDAO;
import com.wulin.dal.task.entity.TaskDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by FengG on 16/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    CheckService checkService;
    @Autowired
    SecurityService securityService;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    ProsessionDAO prosessionDAO;
    @Autowired
    TaskDAO taskDAO;
    @Autowired
    RefreshTask refreshTask;
    @Autowired
    ReportTask reportTask;
    
    @Test
    public void 向表中插入新的用户用户名为空_注册方法_返回错误描述(){
        //随机生成测试账号
        String user_acct = new Date().toString();
        UserDO userDO = new UserDO();
        userDO.setUSER_PWD("1234");
        userDO.setCREATION_TIME(new Date());
        try {
            String retMsg = userService.insertUser(userDO);
            System.out.println(retMsg);
            Assert.assertEquals(ErrorCodeEnum.USER_ACCT_CANNOT_BE_NULL.getDescription(),retMsg);
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    @Test
    public void 向表中插入新的用户_返回成功(){
        //随机生成测试账号
        String user_acct = new Date().toString();
        user_acct = user_acct.substring(1,20);
        user_acct = checkService.deleteAllSpecialCharactors(user_acct);
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT("TEST"+user_acct);
        userDO.setUSER_PWD("kav012590");
        //userDO.setCREATION_TIME(new Date());
        //userDO.setCREATION_TIME(new Date());
        try {
            String retMsg = userService.insertUser(userDO);
            System.out.println(retMsg);
            Assert.assertEquals(UserReturnCodeEnum.USER_INSERT_SUCCESS.getDescription(),retMsg);
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    @Test
    public void 向表中插入新的用户用户名存在_注册方法_返回错误(){
        //随机生成测试账号
        String user_acct = new Date().toString();
        user_acct = user_acct.substring(1,24);
        user_acct = checkService.deleteAllSpecialCharactors(user_acct);
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT("on Jun 20 171306 CST ");
        userDO.setUSER_PWD("kav012590");
        userDO.setCREATION_TIME(new Date());
        try {
            String retMsg = userService.insertUser(userDO);
            System.out.println(retMsg);
            Assert.assertEquals(UserReturnCodeEnum.USER_HAS_ALREADY_EXIST.getDescription(),retMsg);
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue(false);
        }


    }
    @Test
    public void 向表中插入新的用户用户密码为空_注册方法_返回错误描述(){
        //随机生成测试账号
        String user_acct = new Date().toString();
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT(user_acct);
        userDO.setCREATION_TIME(new Date());
        try {
            String retMsg = userService.insertUser(userDO);
            System.out.println(retMsg);
            Assert.assertEquals(ErrorCodeEnum.USER_PWD_CANNOT_BE_NULL.getDescription(),retMsg);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);

        }
    }
    @Test
    public void 向表中插入新的用户_用户账户有特殊字符_注册方法_返回错误描述(){
        //随机生成测试账号
        String user_acct = new Date().toString();
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT("@#$$%qweqeee");
        userDO.setUSER_PWD("123123");
        userDO.setCREATION_TIME(new Date());
        try {
            String retMsg = userService.insertUser(userDO);
            System.out.println(retMsg);
            Assert.assertEquals(ErrorCodeEnum.USER_ACCT_CANNOT_HAVE_SPECIAL_CHARACTORS.getDescription(),retMsg);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);

        }
    }
    @Test
    public void 向表中插入新的用户_用户账户有中文字符_注册方法_返回错误描述(){
        //随机生成测试账号
        String user_acct = new Date().toString();
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT("傻逼才会写中文");
        userDO.setUSER_PWD("123123");
        userDO.setCREATION_TIME(new Date());
        try {
            String retMsg = userService.insertUser(userDO);
            System.out.println(retMsg);
            Assert.assertEquals(ErrorCodeEnum.USER_ACCT_CANNOT_HAVE_CHINESE_CHARACTORS.getDescription(),retMsg);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);

        }
    }
    @Test
    public void 向表中插入新的用户_用户账户过长或者过短_注册方法_返回错误描述(){
        //随机生成测试账号
        String user_acct = new Date().toString();
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT("111");
        userDO.setUSER_PWD("123123");
        userDO.setCREATION_TIME(new Date());
        try {
            String retMsg = userService.insertUser(userDO);
            System.out.println(retMsg);
            Assert.assertEquals(ErrorCodeEnum.USER_ACCT_LENGTH_IS_TOO_LONG_OR_SHORT.getDescription(),retMsg);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        userDO.setUSER_ACCT("1111111111111111111111111111");
        try {
            String retMsg = userService.insertUser(userDO);
            System.out.println(retMsg);
            Assert.assertEquals(ErrorCodeEnum.USER_ACCT_LENGTH_IS_TOO_LONG_OR_SHORT.getDescription(),retMsg);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    @Test
    public void 对密码进行MD5加密_返回MD5加密结果(){
        String pwd = "kav012590";
        String nPwd = securityService.Md5Creator(pwd);
        System.out.println(nPwd);
        Assert.assertEquals("7676A856DE38735AE51495ED39A8706C",nPwd);
    }
    @Test
    public void 登录函数测试_成功登录(){
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT("on Jun 20 171306 CST ");
        userDO.setUSER_PWD("kav012590");
        String result = userService.userLogin(userDO);
        System.out.println(result);
        Assert.assertEquals(UserReturnCodeEnum.USER_LOGIN_SUCCESS.getDescription(),result);
    }
    @Test
    public void 登录函数测试_用户名不存在_登录失败(){
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT("werwerwerw");
        userDO.setUSER_PWD("kav012590");
        String result = userService.userLogin(userDO);
        System.out.println(result);
        Assert.assertEquals(UserReturnCodeEnum.USER_DOES_NOT_EXIST.getDescription(),result);
    }
    @Test
    public void 登录函数测试_用户密码错误_登录失败(){
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT("on Jun 20 171306 CST ");
        userDO.setUSER_PWD("kav01259011");
        String result = userService.userLogin(userDO);
        System.out.println(result);
        Assert.assertEquals(UserReturnCodeEnum.USER_PWD_IS_WRONG.getIndex().toString(),result);
    }

    @Test
    public void testgetRole(){
        int bacnum = roleDAO.checkIfHaveRoleViaUserAcct("tester14");
        System.out.println(bacnum);
    }
    @Test
    public void testGetProsessionList(){
        List<ProsessionDO> psd = prosessionDAO.getProsessionList();
        for (ProsessionDO p : psd) {
            System.out.println(p.getP_NAME());
        }
    }
    @Test
    public void dateTimeTest(){
        TaskDO taskDO = new TaskDO();
        taskDO.setStatus(0);
        taskDO.setMgroup("1");
        taskDO.setProjectName("ttkb");
        List<TaskDO> taskDOs = taskDAO.findVailedTaskByStatusAndGroupAndProjectAndRuntimes(taskDO);
        System.out.println(taskDOs.size());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.sql.Timestamp curDate = new java.sql.Timestamp(System.currentTimeMillis());
        System.out.println(curDate);
    }
    @Test
    public void temp_test(){
        reportTask.runTest();
    }

}
