/**
 * {@code @Description}  md5加密
 *
 * @author liyajun
 * {@code @create}          2022-11-16 22:19
 */

package com.group7.sanSongMall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encode_MD5 {
    /**
     * 加密
     *
     * @param str str
     * @return {@link String}
     */
    public static String encrypt(String str){
        try {
            char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            byte [] bytes = str.getBytes();

            MessageDigest md5 = MessageDigest.getInstance("MD5");

            md5.update(bytes);

            bytes = md5.digest();

            int len = bytes.length;
            char [] chars = new char[len * 2];
            int cnt = 0;
            for (byte b : bytes) {
                chars[cnt++] = hexChars[b >>> 4 & 0xf];
                chars[cnt++] = hexChars[b & 0xf];
            }

            return new String(chars);

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            throw  new RuntimeException("md5加密出错"+e);
        }
    }

}

