/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:00
 */

package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Product;
import com.group7.sanSongMall.util.Result;

public interface productService extends IService<Product> {
    //    根据商品id,获取商品详细信息
    Result getProductById(String id);

    //    根据商品分类id获取首页展示的商品信息
    Result getCarousePic(String categoryId);

    //    分页获取所有的商品信息
    Result getProductPage(IPage<Product> page,Product product);

}
