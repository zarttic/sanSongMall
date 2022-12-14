/**
 * {@code @Description} 解析token
 *
 * @author liyajun
 * {@code @create}          2022-11-16 22:28
 */

package com.group7.sanSongMall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenBody {
    /**
     * 用户账号
     */
    String account;
    /**
     * 用户名
     */
    String username;
    /**
     * 用户类型
     */
    Integer usertype;
}
