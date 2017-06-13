package io.github.sixtrees.controller;

import com.alibaba.fastjson.JSON;
import io.github.sixtrees.po.user.LoginUser;
import io.github.sixtrees.po.user.User;
import io.github.sixtrees.service.user.UserManagerService;
import io.github.sixtrees.tool.Md5;
import io.github.sixtrees.tool.token.AuthUtil;
import io.github.sixtrees.tool.token.JavaWebToken;
import io.github.sixtrees.tool.token.JsonDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author github.com/sxitrees
 * @description 系统管理，主要是页面访问控制
 * @2017-06-12
 */
@Controller
@RequestMapping("/")
public class SystemController {
    @Qualifier("userManagerServiceImpl")
    @Autowired
    private UserManagerService userService;  //注意：此处的service要用注解注入，不能new

    /**
     * 跳转到欢迎页面
     *
     * @return
     */
    @RequestMapping("/welcome")
    public String jumpToWelcome() {
        //System.out.println("11111");
        return "welcome";
    }

    /**
     * 跳转到登录页面
     *
     * @return 登录页面
     */
    @RequestMapping("/Login")
    public String jumpTouserLogin() {
        //System.out.println("11111");
        return "login";
    }

    /**
     * 跳转到主页面
     *
     * @return 网站主页面
     */
    @RequestMapping("/LoginMain")
    public String loginMain() {

        //TODO 这里写写成是用户的文件管理的首页
        return "files";
    }

    /**
     * @param request
     * @return json数据
     * @description 验证用户是否登录
     */
    @ResponseBody
    @RequestMapping(value = "/IsLogin")
    public String IsLogin(HttpServletRequest request) {
        //Map<String, Object> loginInfo = new HashMap<>();
        JsonDataType jsonData = new JsonDataType();
        try {
            //从session拿到token，再解密得到userid
            Map<String, Object> loginInfo = AuthUtil.getClientLoginInfo(request);
            if (loginInfo == null) {
                jsonData.setCode("-1");
                jsonData.setMsg("token解析错误！");
            } else {
                jsonData.setCode("1");
                jsonData.setMsg("token解析成功！");
                jsonData.setData(loginInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(jsonData);
    }

    /**
     * 验证用户登陆是否成功
     *
     * @return jsonStr
     * @author LiJun
     * @date 2017-03-28
     */
    @ResponseBody
    @RequestMapping(value = "/JudgeLogin")
    public String judgeLogin(HttpServletResponse response, String userName, String password) {

        //验证用户登录状态
        LoginUser user = new LoginUser();
        //设置json的数据格式
        JsonDataType jsonData = new JsonDataType();
        //用户表查询条件赋值到user对象中
        user.setUserName(userName);
        user.setPassword(password);
        //将用户密码用md5方式加密
        user.setPassword(Md5.md5(user.getPassword()));
        User currentUser = userService.findUserByQuery(user);
        //如果用户登录失败
        if (currentUser == null) {
            //设置json的数据传送格式
            jsonData.setCode("-1");
            jsonData.setMsg("登录失败！");
            jsonData.setLoginState(2);
        }
        //用户登录成功
        else {
            System.out.println("当前登录用户：" + currentUser.getUserName());
            //把用户登录信息存入token
            Map<String, Object> loginInfo = new HashMap<>();
            loginInfo.put("userID", currentUser.getId());
            loginInfo.put("userName", currentUser.getUserName());
            //loginInfo.put("passWord", currentUser.getPassword());
            //获取token码            
            //String tokenNo = JWT.sign(currentUser, 30L * 24L * 3600L * 1000L);
            String tokenNo = JavaWebToken.createJavaWebToken(loginInfo);
            //System.out.println("TOKEN码为" + tokenNo);
            //将TOKEN存入 本地cookie
            addCookie(response, "tokenNo", tokenNo, 3600);
            //将token存入json数据中
            jsonData.setData(tokenNo);
            //如果当前用户为admin，则loginstate为0
            if (("admin").equals(currentUser.getUserName()))
                jsonData.setLoginState(0);
            else
                jsonData.setLoginState(1);
        }
        return JSON.toJSONString(jsonData);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        /*if(maxAge>0)
            cookie.setMaxAge(maxAge);*/
        response.addCookie(cookie);
    }

    public UserManagerService getUserService() {
        return userService;
    }

    public void setUserService(UserManagerService userService) {
        this.userService = userService;
    }
}