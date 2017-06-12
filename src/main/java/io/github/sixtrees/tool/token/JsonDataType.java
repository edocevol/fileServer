package io.github.sixtrees.tool.token;

/**
 * @author github.com/sxitrees
 * @description 自定义JSON数据格式
 * @2017-06-12
 */
public class JsonDataType {
    //状态码，1代表成功，-1代表异常
    public String code = "1";
    //成功或者异常信息
    public String msg = "success";
    //数据体
    public Object data;
    public Integer loginState;//用户登陆状态，0：管理员登陆 1：普通用户登陆  2：登陆失败

    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}