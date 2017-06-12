package io.github.sixtrees.service.user;

import io.github.sixtrees.po.user.LoginUser;
import io.github.sixtrees.po.user.User;
import io.github.sixtrees.tool.Md5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/springmvc.xml"})
//加上下面这两天语句是为了不影响数据库中的实际的数据
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserManagerServiceTest {
    @Autowired
    UserManagerService userManagerService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword(Md5.md5("admin"));
        System.out.println(user.toString());
        int id = userManagerService.addUser(user);
        System.out.println(id);
    }

    @Test
    public void testSelectUserByUserID() {
        int userId = 3;
        User user = userManagerService.selectUserInfoById(userId);
        System.out.println(user);
    }

    @Test
    public void testFindUserByQuery() {
        LoginUser user = new LoginUser();
        user.setUserName("admin");
        user.setPassword(Md5.md5("admin"));

        User real = userManagerService.findUserByQuery(user);
        System.out.println(real);

    }
}