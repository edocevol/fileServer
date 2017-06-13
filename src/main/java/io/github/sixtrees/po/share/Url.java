package io.github.sixtrees.po.share;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Url")
public class Url {
    private int id;
    private String realParam;
    private String fackParam;
    private Date falseTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealParam() {
        return realParam;
    }

    public void setRealParam(String realParam) {
        this.realParam = realParam;
    }

    public String getFackParam() {
        return fackParam;
    }

    public void setFackParam(String fackParam) {
        this.fackParam = fackParam;
    }

    public Date getFalseTime() {
        return falseTime;
    }

    public void setFalseTime(Date falseTime) {
        this.falseTime = falseTime;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", realParam='" + realParam + '\'' +
                ", fackParam='" + fackParam + '\'' +
                ", falseTime=" + falseTime +
                '}';
    }
}
