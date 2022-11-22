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
import com.group7.sanSongMall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("/shopcarServiceImpl")
@Transactional
public class shopcarServiceImpl extends ServiceImpl<shopcarMapper, Shoppingcart> implements shopcarService {


    @Override
    public Result getshopcar(String id) {
        if (StringUtils.isEmpty(id)){
            return Result.fail().message("信息丢失 请刷新重试");
        }
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return Result.ok(baseMapper.selectList(queryWrapper));
    }

    @Override
    public Result getOneProduct(String userId,String product_id) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", product_id);
        return Result.ok(baseMapper.selectOne(queryWrapper));
    }

    @Override
    public Result updateShopCar(Shoppingcart shoppingcart) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", shoppingcart.getUserId());
        queryWrapper.eq("product_id", shoppingcart.getProductId());
        baseMapper.update(shoppingcart,queryWrapper);
        return Result.ok(baseMapper.selectById(shoppingcart.getId()));
    }

    @Override
    public Result delShopCar(String id) {
        baseMapper.deleteById(id);
        return Result.ok();
    }
}
