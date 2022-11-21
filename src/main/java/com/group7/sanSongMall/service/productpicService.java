/**
 * @Description
 * @author liyajun
 * @create 2022-11-21 20:22
 */


package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.ProductPicture;
import com.group7.sanSongMall.util.Result;

public interface productpicService extends IService<ProductPicture> {
    //    根据商品id,获取商品图片
    Result getProductPic(String id);
}

