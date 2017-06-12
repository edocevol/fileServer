package io.github.sixtrees.mapper.user;

import io.github.sixtrees.po.user.LoginUser;
import io.github.sixtrees.po.user.User;
import org.springframework.stereotype.Component;

/**
 * @author github.com/sxitrees
 * @description user表的mapper文件
 * @2017-06-12
 */
@Component("userMapper")
public interface UserMapper {
    //根据主键查询user的详细信息
    User selectUserInfoById(Integer userID);


    //根据用户名和密码查询用户表
    User selectUserByQuery(LoginUser user);


    //添加用户
    void addUser(User userCustomer);

//    //修改用户
//    void updateUser(User userCustomer);
//
//    //删除用户
//    void deleteUser(int userID);
//
//    //修改密码
//    void updatePwd(User userCustomer);

}