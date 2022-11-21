package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (ProductPicture)实体类
 *
 * @author makejava
 * @since 2022-11-21 20:07:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_picture")
public class ProductPicture {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    
    private Integer productId;
    
    private String productPicture;
    
    private String intro;


}

