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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/collectServiceImpl")
@Transactional
public class collectServiceImpl extends ServiceImpl<collectMapper, Collect> implements collectService {
    //    把收藏商品信息插入数据库
    @Override
    public int addCollect(String userId, String productId) {
        return baseMapper.insert(new Collect(userId,productId));
    }

    //    获取用户的所有收藏商品信息
    @Override
    public List<Collect> getCollects(String userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

    //    获取用户的某个收藏商品信息
    @Override
    public List<Collect> getOneCollect(String userId, String productId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);
        return baseMapper.selectList(queryWrapper);
    }

    //    删除用户的某个收藏商品信息
    @Override
    public int delCollect(String userId, String productId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);

        return baseMapper.delete(queryWrapper);
    }
}
