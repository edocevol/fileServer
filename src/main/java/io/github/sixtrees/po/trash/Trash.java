package io.github.sixtrees.po.trash;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Trash")
public class Trash {
    private int id;
    private int userId;
    private String path;
    private Date falseTime;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getFalseTime() {
        return falseTime;
    }

    public void setFalseTime(Date falseTime) {
        this.falseTime = falseTime;
    }

    @Override
    public String toString() {
        return "Trash{" +
                "id=" + id +
                ", userId=" + userId +
                ", path='" + path + '\'' +
                ", falseTime=" + falseTime +
                '}';
    }
}
