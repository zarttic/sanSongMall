package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2022-11-21 15:20:48
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class orders {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderId;

    private Integer userId;

    private Integer productId;

    private Integer productNum;

    private Double productPrice;

    private String location;

    private Integer state;

    private Integer delay;

    private Timestamp orderTime;


}

