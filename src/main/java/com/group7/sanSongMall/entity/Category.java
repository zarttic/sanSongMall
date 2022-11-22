package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2022-11-21 19:12:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category  {
    @TableId(value = "category_id",type = IdType.AUTO)
    private Integer categoryId;
    
    private String categoryName;


}

