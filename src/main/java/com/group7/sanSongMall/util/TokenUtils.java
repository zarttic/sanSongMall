/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-17 22:32
 */

package com.group7.sanSongMall.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group7.sanSongMall.entity.TokenBody;
import com.group7.sanSongMall.entity.User;

import java.util.Date;

/**
 * 令牌工具类
 *
 * @author liyaj
 * @date 2022/11/17
 */
public class TokenUtils {

    /**
     * 到期时间  默认设置为10H
     */
    private static final long EXPIRE_TIME= 10*60*60*1000;
    /**
     * token加盐
     */
    private static final String TOKEN_SECRET="ljdyaishijin**3nkjnj??";

    /**
     * 生成token
     * @param user
     * @return
     */
    public static String sign(User user){

        String token=null;
        try {
            Date expireAt=new Date(System.currentTimeMillis()+EXPIRE_TIME);
            token = JWT.create()
                    //发行人
                    .withIssuer("auth0")
                    //存放数据
                    .withClaim("account",user.getAccount())
                    .withClaim("role", user.getRole())
                    //过期时间
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {

        }
        return token;
    }


    /**
     * token验证
     * @param token
     * @return
     */
    public static Boolean verify(String token){

        try {
            //创建token验证器
            JWTVerifier jwtVerifier= JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJWT= ((JWTVerifier) jwtVerifier).verify(token);
            System.out.println("认证通过：");
            System.out.println("account: " + decodedJWT.getClaim("account").asString());
            System.out.println("过期时间：      " + decodedJWT.getExpiresAt());
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }

    /**
     * 获取用户帐户
     *
     * @param token 令牌
     * @return {@link String}
     */
    public static String getUserAccount(String token){
        return JWT.decode(token).getClaim("account").asString();
    }

    /**
     * 获得用户名
     *
     * @param token 令牌
     * @return {@link String}
     */
    public String getUsername(String token){
        return JWT.decode(token).getClaim("username").asString();
    }

    /**
     * 得到用户角色
     *
     * @param token 令牌
     * @return {@link String}
     */
    public Integer getUserRole(String token){
        return JWT.decode(token).getClaim("role").asInt();
    }

    /**
     * 使用tokenBody来集合信息
     *
     * @param token 令牌
     * @return {@link TokenBody}
     */
    public TokenBody getTokenMessage(String token){
        return new TokenBody(getUserAccount(token), getUsername(token), getUserRole(token));
    }


}