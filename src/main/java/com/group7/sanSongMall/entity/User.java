/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-16 21:44
 */

package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@TableName("user")
public class User {

    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 账户 作作为登录凭证
     */
    @NotNull("账号不能为空")
    private String account;
    /**
     * 用户名
     */
    private String username;
    /**
     * 电话 作为登录凭证
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 是否被删除
     */
    private Integer isDel;
    /**
     * 角色
     */
    private Integer role;

    public User(@NotNull String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(String userAccount) {
        this.account = userAccount;
    }
}
