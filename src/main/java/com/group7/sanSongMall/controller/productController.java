/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:06
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.service.productService;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/productController")
public class productController {
    @Autowired
    private productService productService;


//    根据商品id,获取商品详细信息
    @ApiOperation("根据商品id,获取商品详细信息")
    @GetMapping("/getProductById")
    public Result getProductById(String id){
        return Result.ok();
    }
//    根据商品分类id获取首页展示的商品信息
    @ApiOperation("根据商品分类id获取首页展示的商品信息")
    @GetMapping("/getCarousePic")
    public Result getCarousePic(String categoryId){
        return productService.getCarousePic(categoryId);
    }
}


