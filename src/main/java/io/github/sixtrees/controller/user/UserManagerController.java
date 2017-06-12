package io.github.sixtrees.controller.user;

import io.github.sixtrees.po.user.User;
import io.github.sixtrees.service.user.UserManagerService;
import io.github.sixtrees.tool.Md5;
import io.github.sixtrees.tool.token.AuthUtil;
import io.github.sixtrees.tool.token.JavaWebToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author github.com/sxitrees
 * @description 用户管理
 * @2017-06-12
 */
@Controller
@RequestMapping("/user")
public class UserManagerController {
    @Autowired
    private UserManagerService userManagerService;

    /**
     * 添加用户
     *
     * @param userCustomer
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Boolean> addUser(User userCustomer, HttpServletRequest request, Integer[] role) throws Exception {
        //从session拿到token，再解密得到userid
        boolean flag = false;
        Map<String, Boolean> map = new HashMap<>();

        Map<String, Object> loginInfo = AuthUtil.getClientLoginInfo(request);
        if (loginInfo != null) {
            Integer userID = (Integer) loginInfo.get("userID");
            User logUser = userManagerService.selectUserInfoById(userID);

            //密码加密
            String pwd = userCustomer.getPassword();
            String newpwd = Md5.md5(pwd);
            userCustomer.setPassword(newpwd);

            Date date = new Date();
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
            userCustomer.setCreateTime(date);
            flag = userManagerService.addUser(userCustomer) > 0 ? true : false;

        }
        map.put("flag", flag);
        return map;
    }

    /**
     * 性别信息获取
     *
     * @return
     */
    @RequestMapping(value = "getSex", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String, String>> getSex() {
        List<Map<String, String>> sex = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("id", "男");
        map1.put("text", "男");
        Map<String, String> map2 = new HashMap<>();
        map2.put("id", "女");
        map2.put("text", "女");
        sex.add(map1);
        sex.add(map2);
        return sex;
    }

    /*
     * 更新用户
     */
    @RequestMapping(value = "updateUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Boolean> updateUser(User userCustomer, Integer[] role) throws Exception {
        Map<String, Boolean> map = new HashMap<>();

        boolean flag = false;
        try {
            flag = userManagerService.updateUser(userCustomer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("flag", flag);
        return map;
    }

    /**
     * 删除用户
     *
     * @param request
     * @param userID
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String, Boolean> deleteUser(HttpServletRequest request, int userID) throws Exception {
        Map<String, Boolean> map = new HashMap<>();
        boolean flag = userManagerService.deleteUser(userID);
        map.put("flag", flag);
        return map;
    }

    /**
     * 根据用户的id，查询用户的信息
     *
     * @param userID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getUserByUserID", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User getUserByUserID(Integer userID) throws Exception {
        User userCustomer = userManagerService.selectUserInfoById(userID);
        return userCustomer;
    }

    /**
     * 修改密码
     *
     * @param OldPassword
     * @param Password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "changePwd", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> changePwd(HttpServletRequest request, HttpServletResponse response, String OldPassword, String Password) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean IsSuccess = false;
        //从session拿到token，再解密得到userid
        Map<String, Object> loginInfo = AuthUtil.getClientLoginInfo(request);
        if (loginInfo != null) {
            Integer userID = (Integer) loginInfo.get("userID");
            String oldPwd = Md5.md5(OldPassword);
            String pwd = (String) loginInfo.get("passWord");
            if (oldPwd.equals(pwd)) {
                IsSuccess = userManagerService.updatePwd(userID, Md5.md5(Password));
                String tokenNo = JavaWebToken.createJavaWebToken(loginInfo);
                deleteCookie(response, "tokenNo", tokenNo);
            } else {
                map.put("message", "您的旧密码输入不正确");
            }
        } else {
            map.put("message", "您没有登录!");
        }
        map.put("IsSuccess", IsSuccess);
        return map;
    }


    @RequestMapping(value = "logout", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean IsSuccess = false;
        try {
            //从session拿到token，再解密得到userid
            Map<String, Object> loginInfo = AuthUtil.getClientLoginInfo(request);
            if (loginInfo != null) {
                String tokenNo = JavaWebToken.createJavaWebToken(loginInfo);
                deleteCookie(response, "tokenNo", tokenNo);
            }
            IsSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("IsSuccess", IsSuccess);
        return map;
    }


    /**
     * 删除cookie
     *
     * @param response
     * @param name     cookie名字
     * @param value    cookie值
     */
    public static void deleteCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}