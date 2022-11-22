/**
 * @Description
 * @author liyajun
 * @create 2022-11-21 16:26
 */


package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Shoppingcart;
import com.group7.sanSongMall.util.Result;

public interface shopcarService extends IService<Shoppingcart> {
//    获取购物车信息
    Result getshopcar(String id);

    Result getOneProduct(String userId,String product_id);

    Result updateShopCar(Shoppingcart shoppingcart);
    Result delShopCar(String id);
}

