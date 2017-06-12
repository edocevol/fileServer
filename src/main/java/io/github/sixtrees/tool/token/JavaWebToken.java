package io.github.sixtrees.tool.token;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * @author LiJun
 * @description 一个JWT的工具类
 * @date 2017-04-09
 */
public class JavaWebToken {

    private static Logger log = Logger.getLogger(JavaWebToken.class);

    private static Key getKeyInstance() {

        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        String s1 = "Testing DatatypeConverter.printBase64Binary";
        String encodeds1 = DatatypeConverter.printBase64Binary(s1.getBytes());
        System.out.println(encodeds1);
        byte[] decodeds1 = DatatypeConverter.parseBase64Binary(encodeds1);

        //byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("APP");	//此处有问题
        Key signingKey = new SecretKeySpec(decodeds1, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //生成token码
    public static String createJavaWebToken(Map<String, Object> claims) {
        JwtBuilder jwtClaims = Jwts.builder().setClaims(claims);
        JwtBuilder signature = jwtClaims.signWith(SignatureAlgorithm.HS256, getKeyInstance());
        return signature.compact();
        //return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    //解码和验证token码
    public static Map<String, Object> verifyJavaWebToken(String jwt) {
        try {

            Map<String, Object> jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return jwtClaims;
        } catch (Exception e) {
            log.error("token校验失败！");
            return null;
        }
    }

}