package com.wusuowei.shiro_jwt.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wusuowei.shiro_jwt.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    //token有效时长30分钟
    private static Long EXPIRE;
    //token的密钥
    private static String SECRET;
    //refresh-expire续期过期时间
    private static Long REFRESHTOKENEXPIRE;

    @Value("${jwt.expire}")
    public void setExpire(Long expire){
        JWTUtil.EXPIRE = expire*1000;
    }

    @Value("${jwt.secret}")
    public void setSecret(String secret){
        JWTUtil.SECRET = secret;
    }

    @Value("${jwt.refreshtoken-expire}")
    public void setRefreshExpire(Long refreshExpire){
        JWTUtil.REFRESHTOKENEXPIRE = refreshExpire;
    }
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil2;

    private static RedisUtil redisUtil;

    @PostConstruct
    public void init(){
        JWTUtil.redisUtil = redisUtil2;
    }

    public static String createToken(User user) throws UnsupportedEncodingException {
        //token过期时间
        Date date=new Date(System.currentTimeMillis()+EXPIRE);

        //Date now = new Date();
        long now = System.currentTimeMillis();

        //jwt的header部分
        Map<String ,Object> map=new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");

        //使用jwt的api生成token
        String token= JWT.create()
                .withHeader(map)
                .withClaim("uid", user.getId().toString())//私有声明
                .withExpiresAt(date)//过期时间
                .withIssuedAt(new Date(now))//签发时间
                .sign(Algorithm.HMAC256(SECRET));//签名
        redisUtil.hset("refreshToken",String.valueOf(user.getId()),Long.valueOf((long) Math.floor(now/1000)), REFRESHTOKENEXPIRE);
        return token;
    }

    //校验token的有效性，token的header和payload是否没改过
    public static boolean verify(String token){
        try {
            //解密
            JWTVerifier verifier=JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            return true;
        }catch (TokenExpiredException e){
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean isJwtExpired(String token){
        /**
         * @desc 判断token是否过期
         * @author lj
         */
        try {
            DecodedJWT decodeToken = JWT.decode(token);
            return decodeToken.getExpiresAt().before(new Date());
        } catch(Exception e){
            return true;
        }
    }
    //无需解密也可以获取token的信息
    public static String getUserId(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uid").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    //无需解密也可以获取token的信息
    public static String getAccessToken(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return String.valueOf(jwt.getIssuedAt().getTime()/1000);
        } catch (JWTDecodeException e) {
            return "";
        }
    }

}