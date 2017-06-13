package io.github.sixtrees.po.log;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Log")
public class Log {
    private int id;
    private int userId;
    private String userName;
    private String logType;
    private String logDetail;
    private Date logTime;
    private String logIp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogDetail() {
        return logDetail;
    }

    public void setLogDetail(String logDetail) {
        this.logDetail = logDetail;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", logType='" + logType + '\'' +
                ", logDetail='" + logDetail + '\'' +
                ", logTime=" + logTime +
                ", logIp='" + logIp + '\'' +
                '}';
    }
}
