package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * (Collect)实体类
 *
 * @author makejava
 * @since 2022-11-21 20:10:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("collect")
public class Collect {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    
    private Integer userId;
    
    private Integer productId;
    
    private Timestamp collectTime;


    public Collect(String userId, String productId) {
        this.userId = Integer.valueOf(userId);
        this.productId = Integer.valueOf(productId);
    }
}

