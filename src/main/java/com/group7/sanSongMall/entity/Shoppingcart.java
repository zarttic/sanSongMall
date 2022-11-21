package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Shoppingcart)实体类
 *
 * @author makejava
 * @since 2022-11-21 16:17:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shoppingcart")
public class Shoppingcart {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    
    private Integer userId;
    
    private Integer productId;
    
    private Integer num;


}

