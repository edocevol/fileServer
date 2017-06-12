package io.github.sixtrees.service.user;

import io.github.sixtrees.po.user.LoginUser;
import io.github.sixtrees.po.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserManagerService {

    //查询user表
    public User findUserByQuery(LoginUser user);

    //删除user
    public boolean deleteUser(int userID);

    //修改密码
    public boolean updatePwd(Integer userID, String password);

    //根据用户的ID来获取该用户的信息
    public User selectUserInfoById(Integer userID);


    int addUser(User userCustomer);

    boolean updateUser(User userCustomer);

}