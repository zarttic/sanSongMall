package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Carousel)实体类
 *
 * @author makejava
 * @since 2022-11-21 20:09:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("carousel")
public class Carousel {
    @TableId(value = "carousel_id",type = IdType.AUTO)
    private Integer carouselId;
    
    private String imgpath;
    
    private String describes;

}

