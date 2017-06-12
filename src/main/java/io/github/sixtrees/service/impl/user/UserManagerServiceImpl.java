package io.github.sixtrees.service.impl.user;

import io.github.sixtrees.mapper.user.UserMapper;
import io.github.sixtrees.po.user.LoginUser;
import io.github.sixtrees.po.user.User;
import io.github.sixtrees.service.user.UserManagerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by LY on 2017/3/28.
 * <p>Title: ${type_name}</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author ${user}
 * @date ${date} ${time}
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {
    Logger logger = Logger.getLogger(UserManagerServiceImpl.class);

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public boolean deleteUser(int userID) {
        return false;
    }

    /**
     * 根据查询条件查询用户信息的service
     *
     * @param user
     * @return List<user> users
     * @author LiJun
     * @date 2017-03-28
     */
    public User findUserByQuery(LoginUser user) {
        if(user.getUserName()==null || user.getPassword()==null) {
            return null;
        }else{
            return userMapper.selectUserByQuery(user);
        }
    }

    @Override
    public boolean updatePwd(Integer userID, String password) {
        return false;
    }

    @Override
    public User selectUserInfoById(Integer userID){
        if(userID==-1) {
            return null;
        }else{
            return userMapper.selectUserInfoById(userID);
        }
    }

    @Override
    public int addUser(User userCustomer) {
        userMapper.addUser(userCustomer);
        return userCustomer.getId();
    }

    @Override
    public boolean updateUser(User userCustomer) {
        return false;
    }


}