/**
 * @Description
 * @author liyajun
 * @create 2022-11-21 16:26
 */


package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Shoppingcart;

import java.util.List;

public interface shopcarService extends IService<Shoppingcart> {
    //    获取购物车信息
    List<Shoppingcart> getshopcar(String id);

    Shoppingcart getOneProduct(String userId, String product_id);

    int updateShopCar(Shoppingcart shoppingcart);

    int delShopCar(String id);
}

