/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:06
 */

package com.group7.sanSongMall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sanSongMall.entity.Product;
import com.group7.sanSongMall.service.productService;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/productController")
public class productController {
    @Autowired
    private productService productService;

    @Autowired
    private com.group7.sanSongMall.service.categoryService categoryService;


    //    根据商品id,获取商品详细信息
    @ApiOperation("根据商品id,获取商品详细信息")
    @GetMapping("/getProductById")
    public Result getProductById(String id) {

        return Result.ok(productService.getProductById(id));
    }

    //    根据商品分类id获取首页展示的商品信息
    @ApiOperation("根据商品分类名称获取首页展示的商品信息")
    @GetMapping("/getCarousePic")
    public Result getCarousePic(String categoryName) {

        return Result.ok(
                productService.getCarousePic(
                        categoryService.getCategoryByName(categoryName).getCategoryId().toString()
                )
        );
    }

    //    分页获取所有的商品信息
    @ApiOperation("分页获取所有的商品信息")
    @GetMapping("/getProductPage/{pageNo}/{pageSize}")
    public Result getProductPage(
            @ApiParam("查询页码") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页面大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("商品模糊查询") @RequestBody Product product) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        return Result.ok(productService.getProductPage(page, null));

    }

    //    根据商品分类id,分页获取商品信息
    @ApiOperation("根据商品分类id,分页获取商品信息")
    @GetMapping("/getProductPageBycategory/{pageNo}/{pageSize}")
    public Result getProductPageBycategory(@ApiParam("查询页码") @PathVariable("pageNo") Integer pageNo,
                                           @ApiParam("页面大小") @PathVariable("pageSize") Integer pageSize,
                                           @ApiParam("分类id") String categoryID) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        Product product = new Product();
        product.setCategoryId(Integer.valueOf(categoryID));
        return Result.ok(productService.getProductPage(page, product));
    }

    //    根据搜索条件,分页获取商品信息
    @ApiOperation("分类筛选商品,分页获取商品信息")
    @GetMapping("/getPages/{pageNo}/{pageSize}")
    public Result getPages(@ApiParam("查询页码") @PathVariable("pageNo") Integer pageNo,
                           @ApiParam("页面大小") @PathVariable("pageSize") Integer pageSize,
                           @ApiParam("分类id") Product product) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        return Result.ok(productService.getProductPage(page, product));
    }
}


