package io.github.sixtrees.controller;

import io.github.sixtrees.tool.GenerateVerificationPic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author github.com/sxitrees
 * @description 验证码生成及校验
 * @2017-06-12
 */
@Controller
@RequestMapping("/")
public class VerificationCodeController {

    private ByteArrayInputStream pic;
    private byte[] picture;
    private Map<String, Object> session;
    private String code;
    private int type;
    private String errMsg;

    /**
     * 生成验证码
     *
     * @author LiJun
     * @ date 2017-3-27
     */
    @RequestMapping(value = "/loginCode", method = {RequestMethod.POST, RequestMethod.GET})
    public void loginCode(HttpServletResponse response, HttpSession session) throws IOException {
        int width = 138, height = 40;
        GenerateVerificationPic generate = new GenerateVerificationPic(width, height);//绘制图片
        StringBuffer code = generate.generateVerificationCode();    //绘制验证码
        //将验证码图片转为byte字节流
        picture = generate.generateVerificationPic(code);
        while (picture == null) {
            picture = generate.generateVerificationPic(code);
        }
        if (picture != null) {
            //设置response返回的数据格式
            response.setContentType("image/png");
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(picture);
            responseOutputStream.flush();
            responseOutputStream.close();
            session.setAttribute("VerifyCode", code.toString());    //将验证码存储在session中

        }
    }

    /**
     * 验证码校验
     *
     * @author LiJun
     * @ date 2017-3-27
     */
    //注意使用JSON一定要加此注解
    @RequestMapping(value = "/VerifyCode")
    public
    @ResponseBody
    Map<String, String> verifyCode(HttpSession session) {
        code = (String) session.getAttribute("VerifyCode");
        //System.out.println(code);
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        return map;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public ByteArrayInputStream getPic() {
        return pic;
    }

    public void setPic(ByteArrayInputStream pic) {
        this.pic = pic;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
