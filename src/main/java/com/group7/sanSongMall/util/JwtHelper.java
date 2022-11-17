/**
 * {@code @Description} 弃用
 *
 * @author liyajun
 * {@code @create}          2022-11-16 22:30
 */

package com.group7.sanSongMall.util;




import com.group7.sanSongMall.entity.TokenBody;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;


import java.util.Date;

public class JwtHelper {

    /**
     * 令牌过期时间  默认为24小时
     */
    private static final long tokenExpirationTime = 24 * 60 * 60 * 1000;
    /**
     * 加盐
     */
    private static final String tokenSignKey = "123456";

    /**
     * 创建令牌
     *
     * @param userType 用户类型
     * @param userId   用户id
     * @return {@link String}
     */
    public static String createToken(Long userId,Integer userType,String userName){
        String token = Jwts.builder()
                .setSubject("USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .claim("userId",userId)
                .claim("userType",userType)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512,tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 得到用户id
     *
     * @param token 令牌
     * @return {@link Long}
     */
    public static Long getUserID(String token){
        Claims claims = getClaims(token);
        if (isClaimsNull(claims))return null;
        Integer userId = (Integer) claims.get("userId");
        return userId.longValue();
    }

    /**
     * 得到用户类型
     *
     * @param token 令牌
     * @return {@link Integer}
     */
    public static Integer getUserType(String token){
        Claims claims = getClaims(token);
        if (isClaimsNull(claims))return null;
        return (Integer) claims.get("userType");

    }

    /**
     * 获得用户名
     *
     * @param token 令牌
     * @return {@link String}
     */
    public static String getUserName(String token){
        Claims claims = getClaims(token);
        if (isClaimsNull(claims))return null;
        return (String) claims.get("userName");
    }

    /**
     * 是否过期
     *
     * @param token 令牌
     * @return boolean
     */
    public static boolean isExpiration(String token){
        try{
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e){
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 令牌
     * @return {@link String}
     */
    public String refreshToken(String token){
        String refreshToken;
        try {
//            final Claims claims = getClaims(token);
            refreshToken = JwtHelper.createToken(getUserID(token), getUserType(token),getUserName(token));
        } catch (Exception e){
            refreshToken = null;
        }
        return refreshToken;
    }

    /**
     * 判断是否为空
     *
     * @param c c
     * @return boolean
     */
    public static boolean isClaimsNull(Claims c){
        return c == null;
    }

    /**
     * 身份信息抽取
     *
     * @param token 令牌
     * @return {@link Claims}
     */
    private static Claims getClaims(String token){
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    /**
     * 从令牌里面解析信息
     *
     * @param token 令牌
     * @return {@link TokenBody}
     */
    public static TokenBody getTokenMessage(String token){
        return new TokenBody(getUserID(token),getUserName(token),getUserType(token));
    }

}

