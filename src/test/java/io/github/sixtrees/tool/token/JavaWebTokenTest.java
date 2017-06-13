package io.github.sixtrees.tool.token;

import org.junit.Test;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
public class JavaWebTokenTest {
    @Test
    public void createJavaWebToken() throws Exception {

    }

    @Test
    public void verifyJavaWebToken() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzV29yZCI6IklTTXZLWHBYcGFkRGlVb09Tb0Fmd3c9PSIsInVzZXJOYW1lIjoiYWRtaW4iLCJ1c2VySUQiOjB9.kaSMsb0morAmJhe_Iem22rmEhQ3vMCnKutZipCkOHbA";
        Map<String, Object> map = JavaWebToken.verifyJavaWebToken(token);
        for (String key : map.keySet()) {
            System.out.println(map.get(key) + "\r\n");
        }
    }

}