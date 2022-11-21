/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:00
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.Product;
import com.group7.sanSongMall.mapper.productMapper;
import com.group7.sanSongMall.service.productService;
import com.group7.sanSongMall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("/productServiceImpl")
@Transactional
public class productServiceImpl extends ServiceImpl<productMapper, Product> implements productService {
    //    根据商品id,获取商品详细信息
    @Override
    public Result getProductById(String id) {
        return Result.ok(baseMapper.selectById(id));
    }

    //    根据商品分类id获取首页展示的商品信息
    @Override
    public Result getCarousePic(String categoryId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        return Result.ok(baseMapper.selectList(queryWrapper));
    }

    @Override
    public Result getProductPage(IPage<Product> page, Product product) {
        //如果要加一些参数的话校验product参数
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(product.getProductName())) {
            queryWrapper.like("product_name", product.getProductName());
        }

        if (!StringUtils.isEmpty(product.getProductTitle())) {
            queryWrapper.like("product_title", product.getProductTitle());
        }

        if (!StringUtils.isEmpty(product.getProductIntro())) {
            queryWrapper.like("product_intro", product.getProductIntro());
        }
        return Result.ok(baseMapper.selectPage(page, queryWrapper));
    }



}
