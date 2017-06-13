package io.github.sixtrees.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.Cookie;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/spring/springmvc.xml"})
public class SystemControllerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    final Logger logger = LoggerFactory.getLogger(SystemControllerTest.class);
    @Autowired
    private SystemController systemController;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void judgeLogin() throws Exception {
        String json = systemController.judgeLogin(response, "admin", "admin");
        System.out.println(json);
    }

    @Test
    public void isLogin() throws Exception {
        String code = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwidXNlcklEIjowfQ.i992zXZ5CpV4IqeXPb7rlAab5fa_aQM8AE_eR1wCkK0";
        request.setCookies(new Cookie("tokenNo", code));
        String json = systemController.IsLogin(request);
        Assert.assertNotNull(json);
        logger.info(json);
    }

}