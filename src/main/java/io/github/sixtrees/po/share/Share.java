package io.github.sixtrees.po.share;

import org.apache.ibatis.type.Alias;

@Alias("Share")
public class Share {
    private int id;
    private int urlId;
    private String allowAll;
    private String password;

    @Override
    public String toString() {
        return "Share{" +
                "id=" + id +
                ", urlId=" + urlId +
                ", allowAll='" + allowAll + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }

    public String getAllowAll() {
        return allowAll;
    }

    public void setAllowAll(String allowAll) {
        this.allowAll = allowAll;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
