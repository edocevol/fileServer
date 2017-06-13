package io.github.sixtrees.po.user;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author LiJun
 * @version 1.0
 * @category 用户表PO对象
 * @Date 2017-3-19
 */
@Alias("User")
public class User {

    private int id;
    private String userName;
    private String password;
    private String nickName;
    private Date createTime;
    private Date lastLoginTime;
    private String lastLoginIp;
    private String userEmail;
    private String passwordQuestion;
    private String passwordAnswer;
    private String rootPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPasswordQuestion() {
        return passwordQuestion;
    }

    public void setPasswordQuestion(String passwordQuestion) {
        this.passwordQuestion = passwordQuestion;
    }

    public String getPasswordAnswer() {
        return passwordAnswer;
    }

    public void setPasswordAnswer(String passwordAnswer) {
        this.passwordAnswer = passwordAnswer;
    }


    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", passwordQuestion='" + passwordQuestion + '\'' +
                ", passwordAnswer='" + passwordAnswer + '\'' +
                ", rootPath='" + rootPath + '\'' +
                '}';
    }
}
