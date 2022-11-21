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

@Service("/collectServiceImpl")
@Transactional
public class collectServiceImpl extends ServiceImpl<collectMapper, Collect> implements collectService {
    //    把收藏商品信息插入数据库
    @Override
    public Result addCollect(Collect collect) {
        baseMapper.insert(collect);
        return Result.ok();
    }

    //    获取用户的所有收藏商品信息
    @Override
    public Result getCollects() {
        return Result.ok(baseMapper.selectList(null));
    }
    //    获取用户的某个收藏商品信息
    @Override
    public Result getOneCollect(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId());
        queryWrapper.eq("product_id", collect.getProductId());
        return Result.ok(baseMapper.selectList(queryWrapper));
    }

    //    删除用户的某个收藏商品信息
    @Override
    public Result delCollect(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId());
        queryWrapper.eq("product_id", collect.getProductId());
        baseMapper.delete(queryWrapper);
        return Result.ok();
    }
}
