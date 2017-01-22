package MvcTest;

import com.wulin.web.controller.user.UserAction;
import com.wulin.biz.common.constants.SessionEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by FengG on 16/6/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/applicationContext.xml", "classpath:/springmvc-servlet.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserActionTest {
    @Autowired
    private UserAction userAction;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;

    @Autowired
    private WebApplicationContext webApplicationContext;

    // 执行测试方法之前初始化模拟request,response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
        response.setContentType("text/html;charset=UTF-8");
        mockHttpSession = new MockHttpSession();
    }

    @Test
    public void testGetUser(){
        try {
            request.addParameter("USER_ACCT","tester02");
            request.addParameter("USER_PWD","kav012590");
            //System.out.println(userAction.regUser(request,response));
            System.out.println(userAction.loginUser(request,response,mockHttpSession));
            System.out.println("access_token : "+mockHttpSession.getAttribute(SessionEnum.CurrentAccessToken.getEnumKey()));
            System.out.println("last_login_time : "+mockHttpSession.getAttribute("USER_LOGIN_TIME"));
            System.out.println("user_acct : "+mockHttpSession.getAttribute("USER_ACCT"));
            //System.out.println(userAction.regUser());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void testGetUserByUrl(){
        try {
            mockMvc.perform((post("/user/get").param("userName", "admin").param("password", "1"))).andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void regUser(){
        try {
            mockMvc.perform((post("/user/register.do").param("USER_ACCT", "tester13").param("USER_PWD", "kav012590").param("USER_CCODE","_t__shadow"))).andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void userLogin(){
        try {
            mockMvc.perform((post("/user/login.do").param("USER_ACCT", "tester13").param("USER_PWD", "kav012590"))).andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void 获取所有的服务器列表(){
        try {
            mockMvc.perform((post("/server/getallserverlist"))).andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void 检测已经登录的用户(){
        try {
            mockMvc.perform((post("/user/login.do").param("USER_ACCT", "tester02").param("USER_PWD", "kav012590"))).andExpect(status().isOk())
                    .andDo(print());
            mockMvc.perform((post("/user/checklogin.do"))).andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
