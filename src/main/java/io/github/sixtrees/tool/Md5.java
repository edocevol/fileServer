package io.github.sixtrees.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class Md5 {
	// 使用MD5加密密码  
    public static String md5(String message) {  
        try {  
            MessageDigest md;  
  
            md = MessageDigest.getInstance("md5");  
            byte m5[] = md.digest(message.getBytes());  
            BASE64Encoder encoder = new BASE64Encoder();  
            return encoder.encode(m5);  
        } catch (NoSuchAlgorithmException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
        return null;  
    }  
    public static void main(String[] args) {
	}
}
