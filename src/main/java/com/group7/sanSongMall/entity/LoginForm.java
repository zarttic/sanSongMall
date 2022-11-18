/**
 * {@code @Description}  提交登录信息 包括验证码
 *
 * @author liyajun
 * {@code @create}          2022-11-18 14:22
 */

package com.group7.sanSongMall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录表单
 *
 * @author liyaj
 * @date 2022/11/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    /**
     * 账户
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String verifyCode;

}
