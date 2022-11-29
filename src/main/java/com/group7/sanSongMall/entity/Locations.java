/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-26 22:01
 */

package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("location")
public class Locations implements Serializable {

    private static final long serialVersionUID = -12948971590000672L;
    /**
     * 自增id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 收货人姓名
     */
    private String username;
    /**
     * 收获人手机号
     */
    private String phone;
    /**
     * 地址串
     */
    private String location;
    /**
     * 备注 例如 学校 家
     */
    private String tabs;

}
