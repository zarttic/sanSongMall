/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:23
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.ProductPicture;
import com.group7.sanSongMall.mapper.productpicMapper;
import com.group7.sanSongMall.service.productpicService;
import com.group7.sanSongMall.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("/productpicService")
@Transactional
public class productpicServiceImpl  extends ServiceImpl<productpicMapper, ProductPicture> implements productpicService {
    //根据商品id获取商品图片
    @Override
    public Result getProductPic(String id) {
        QueryWrapper<ProductPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", id);

        return Result.ok(baseMapper.selectList(queryWrapper));
    }
}
