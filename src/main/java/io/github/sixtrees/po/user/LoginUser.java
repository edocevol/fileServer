package io.github.sixtrees.po.user;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public class LoginUser {
    private String userName;
    private String password;


    public LoginUser() {
    }

    public LoginUser(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
