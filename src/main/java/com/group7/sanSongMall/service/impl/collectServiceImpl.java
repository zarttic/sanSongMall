/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:22
 */

package com.group7.sanSongMall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.Collect;
import com.group7.sanSongMall.mapper.collectMapper;
import com.group7.sanSongMall.service.collectService;
import com.group7.sanSongMall.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/collectServiceImpl")
@Transactional
public class collectServiceImpl extends ServiceImpl<collectMapper, Collect> implements collectService {
    //    把收藏商品信息插入数据库
    @Override
    public Result addCollect(String userId,String productId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);
        List<Collect> collects = baseMapper.selectList(queryWrapper);
        if (collects.size() > 0){
            return Result.fail().message("此商品已添加收藏,请勿重复添加");
        }
        baseMapper.insert(new Collect(userId,productId));
        return Result.ok();
    }

    //    获取用户的所有收藏商品信息
    @Override
    public Result getCollects() {
        return Result.ok(baseMapper.selectList(null));
    }
    //    获取用户的某个收藏商品信息
    @Override
    public Result getOneCollect(String userId,String productId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);
        return Result.ok(baseMapper.selectList(queryWrapper));
    }

    //    删除用户的某个收藏商品信息
    @Override
    public Result delCollect(String userId,String productId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);
        baseMapper.delete(queryWrapper);
        return Result.ok();
    }
}
