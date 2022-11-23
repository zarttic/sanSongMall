/**
 * @Description
 * @author liyajun
 * @create 2022-11-21 20:20
 */


package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Carousel;

import java.util.List;

public interface carouselService extends IService<Carousel> {
    //获取轮播图数据
    public List<Carousel> getPic();
}
