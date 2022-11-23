/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 16:26
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.Shoppingcart;
import com.group7.sanSongMall.mapper.shopcarMapper;
import com.group7.sanSongMall.service.shopcarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/shopcarServiceImpl")
@Transactional
public class shopcarServiceImpl extends ServiceImpl<shopcarMapper, Shoppingcart> implements shopcarService {


    @Override
    public List<Shoppingcart> getshopcar(String id) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Shoppingcart getOneProduct(String userId, String product_id) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", product_id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateShopCar(Shoppingcart shoppingcart) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", shoppingcart.getUserId());
        queryWrapper.eq("product_id", shoppingcart.getProductId());

        return baseMapper.update(shoppingcart,queryWrapper);
    }

    @Override
    public int delShopCar(String id) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return baseMapper.delete(queryWrapper);
    }
}
