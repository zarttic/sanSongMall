/**
 * {@code @Description}
 * 接口测试完成 2022-11-22
 *
 * @author liyajun
 * {@code @create}          2022-11-21 16:36
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.controller.dto.shopcarDTO;
import com.group7.sanSongMall.entity.Shoppingcart;
import com.group7.sanSongMall.service.shopcarService;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "购物车管理")
@RestController
@RequestMapping("/shopcarController")
public class shopcarController {

    @Autowired
    private shopcarService shopcarService;
    @Autowired
    private com.group7.sanSongMall.service.productService productService;

    /**
     * 购物车详细信息
     *
     * @param id id
     * @return {@link Result}
     */// 获取购物车信息
    @ApiOperation("获取购物车信息")
    @GetMapping("/getshopcar")
    public Result getshopcar(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Result.fail().message("信息丢失 请刷新重试");
        }
        List<Shoppingcart> shopcar = shopcarService.getshopcar(userId);
        List<shopcarDTO> ans = new ArrayList<>();
//        Map<Product,Integer> ans = new HashMap<>();
        for (Shoppingcart shoppingcart : shopcar) {
            ans.add(new shopcarDTO(productService.getProductById(String.valueOf(shoppingcart.getProductId())),shoppingcart.getNum()));
        }
        return Result.ok(ans);
    }

    /**
     * 筛选出购物车里面的一个商品
     * 传入参数 user_id  product_id
     *
     * @param
     * @return {@link Result}
     */// 查询用户的购物车的某个商品
    @ApiOperation("查询购物车的某个商品")
    @GetMapping("/getOneOfShopcar")
    public Result findOneProduct(String userId, String productId) {
        Shoppingcart one = shopcarService.getOneProduct(userId, productId);

        if (one == null) return Result.fail().message("未查询到");
        return Result.ok(one);
    }

    /**
     * 没有什么用处 但是我不想删除
     *
     * @param shoppingcart shoppingcart
     * @return {@link Result}
     */// 新插入购物车信息  除了id都要传输
    @ApiOperation("新增或者更新购物车商品")
    @PostMapping("/addShopCar")
    public Result addShopCar(@RequestBody Shoppingcart shoppingcart) {
        return Result.ok(shopcarService.saveOrUpdate(shoppingcart));
    }

    // 更新购物车商品数量  参数需要全部传入
    @ApiOperation("更新购物车商品数量")
    @PostMapping("/updateShopCar")
    public Result updateShopCar(@RequestBody Shoppingcart shoppingcart) {
        if (shopcarService.updateShopCar(shoppingcart) > 0) {
            return Result.ok(findOneProduct(
                    shoppingcart.getUserId().toString(),
                    shoppingcart.getProductId().toString())).message("更新成功");
        }
        return Result.fail().message("放入购物车成功");

    }
    // 删除购物车信息  传入id进行删除

    @ApiOperation("删除购物车信息")
    @DeleteMapping("/delShopCar")
    public Result delShopCar(String id) {
        if (shopcarService.delShopCar(id) > 0) Result.ok(true).message("删除购物车成功");
        return Result.fail(false).message("购物车为空");
    }
}
