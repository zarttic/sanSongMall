/**
 * @Description
 * @author liyajun
 * @create 2022-11-21 20:22
 */


package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Collect;
import com.group7.sanSongMall.util.Result;

public interface collectService extends IService<Collect> {
//    收藏商品信息插入数据库
    Result addCollect(String userId,String productId);

    //    获取用户的所有收藏商品信息
    Result getCollects();

    //    获取用户的某个收藏商品信息
    Result getOneCollect(String userId,String productId);
    //    删除用户的某个收藏商品信息
    Result delCollect(String userId,String productId);
}
