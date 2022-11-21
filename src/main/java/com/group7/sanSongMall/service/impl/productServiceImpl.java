/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:00
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.Product;
import com.group7.sanSongMall.mapper.productMapper;
import com.group7.sanSongMall.service.productService;
import com.group7.sanSongMall.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("/productServiceImpl")
@Transactional
public class productServiceImpl extends ServiceImpl<productMapper, Product> implements productService {
    @Override
    public Result getProductById(String id) {
//        QueryWrapper
        return null;
    }
//    根据商品分类id获取首页展示的商品信息
    @Override
    public Result getCarousePic(String categoryId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        return Result.ok(baseMapper.selectList(queryWrapper));
    }


}
