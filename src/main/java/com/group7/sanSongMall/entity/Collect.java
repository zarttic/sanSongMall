package com.group7.sanSongMall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer id;
    
    private Integer userId;
    
    private Integer productId;
    
    private Long collectTime;


}

