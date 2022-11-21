package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2022-11-21 19:28:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer productId;
    
    private String productName;
    
    private Integer categoryId;
    
    private String productTitle;
    
    private String productIntro;
    
    private String productPicture;
    
    private String productPrice;
    
    private String productSellingPrice;
    
    private Integer productNum;
    
    private Integer productSales;


}

