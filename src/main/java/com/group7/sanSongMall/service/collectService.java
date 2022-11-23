/**
 * @Description
 * @author liyajun
 * @create 2022-11-21 20:22
 */


package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Collect;

import java.util.List;

public interface collectService extends IService<Collect> {
//    收藏商品信息插入数据库
    int addCollect(String userId, String productId);

    //    获取用户的所有收藏商品信息
    List<Collect> getCollects(String userId);

    //    获取用户的某个收藏商品信息
    List<Collect> getOneCollect(String userId, String productId);
    //    删除用户的某个收藏商品信息
    int delCollect(String userId, String productId);
}
