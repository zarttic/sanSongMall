/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 16:36
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.entity.Shoppingcart;
import com.group7.sanSongMall.service.shopcarService;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "购物车管理")
@RestController
@RequestMapping("/ordersController")
public class shopcarController {

    @Autowired
    private shopcarService shopcarService;
    // 获取购物车信息
    @ApiOperation("获取购物车信息")
    @GetMapping("/getshopcar")
    public Result getshopcar(String id){
        return shopcarService.getshopcar(id);
    }

    /**
     * 筛选出购物车里面的一个商品
     * 传入参数 user_id  product_id
     *
     * @param shoppingcart shoppingcart
     * @return {@link Result}
     */// 查询用户的购物车的某个商品
    @ApiOperation("查询购物车的某个商品")
    public Result findOneProduct(@RequestBody Shoppingcart shoppingcart){
        return shopcarService.getOneProduct(shoppingcart);
    }
    // 新插入购物车信息
    @ApiOperation("新增或者更新购物车商品")
    @PostMapping("/addShopCar")
    public Result addShopCar(@RequestBody Shoppingcart shoppingcart){
        return Result.ok(shopcarService.saveOrUpdate(shoppingcart));
    }
    // 更新购物车商品数量
    @ApiOperation("更新购物车商品数量")
    @GetMapping("/updateShopCar")
    public Result updateShopCar(@RequestBody Shoppingcart shoppingcart){
        return shopcarService.updateShopCar(shoppingcart);
    }
    // 删除购物车信息

    @ApiOperation("删除购物车信息")
    @DeleteMapping("/delShopCar")
    public Result delShopCar(@RequestBody Shoppingcart shoppingcart){
        return shopcarService.delShopCar(shoppingcart);
    }
}
