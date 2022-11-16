/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-16 23:58
 */

package com.group7.sanSongMall.util;

import javax.servlet.http.HttpServletRequest;

public class AuthContextHolder {
    /**
     * 得到用户id标记
     *
     * @param request 请求
     * @return {@link Long}
     */
    public static Long getUserIdToken(HttpServletRequest request){
        return JwtHelper.getUserID(request.getHeader("token"));
    }

    /**
     * 得到用户名令牌
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getUserNameToken(HttpServletRequest request){
        return JwtHelper.getUserName( request.getHeader("token"));
    }

    /**
     * 得到用户类型令牌
     *
     * @param request 请求
     * @return {@link Integer}
     */
    public static Integer getUserTypeToken(HttpServletRequest request){
        return JwtHelper.getUserType( request.getHeader("token"));
    }
}

