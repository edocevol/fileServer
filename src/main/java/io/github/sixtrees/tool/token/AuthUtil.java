package io.github.sixtrees.tool.token;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
/**
 * @description 解密token码 ,返回登录用户信息
 * @author LiJun
 *
 */
public class AuthUtil {
    public static Map<String, Object> getClientLoginInfo(HttpServletRequest request) throws Exception {
        Map<String, Object> loginInfo = new HashMap<>();
        //从本地COOKIE中获取token
        Cookie tokenNo=getCookieByName(request,"tokenNo");
        //如果tokenNo在cookie中不存在
        if(tokenNo==null){
        	System.out.println("token不存在");
        	return null;
        }
        else{
        	String sessionID=tokenNo.getValue();
        	loginInfo = decodeSession(sessionID);
        	return loginInfo;
        }
    }
    public static Long getUserId(HttpServletRequest request) throws Exception {
    	
        return  Long.valueOf((Integer)getClientLoginInfo(request).get("userID"));

    }

    /**
     * session解密
     */
    public static Map<String, Object> decodeSession(String sessionId) {
        try {
            return JavaWebToken.verifyJavaWebToken(sessionId);
        } catch (Exception e) {
            System.err.println("");
            return null;
        }
    }
    /**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}