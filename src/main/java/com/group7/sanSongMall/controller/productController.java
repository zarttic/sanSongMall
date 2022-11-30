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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/productController")
public class productController {
    @Autowired
    private productService productService;

    @Autowired
    private com.group7.sanSongMall.service.categoryService categoryService;

    @ApiOperation("修改商品")
    @PostMapping("/updPro")
    public Result updPro(@RequestBody Product product){
        System.out.println(product);
        if (product.getProductId() == null){
            product.setProductSales(0);
            productService.save(product);
            return Result.ok().message("新增商品成功");
        }
        if (productService.updateById(product))return Result.ok().message("更新成功");
        return Result.fail().message("更新失败 请重试");
    }

    @ApiOperation("批量删除商品")
    @DeleteMapping("/deleteProByIds")
    public Result deleteProByIds(@RequestBody List<Integer> ids) {
        if (productService.removeByIds(ids)) return Result.ok().message("删除成功");
        return Result.fail().message("删除失败");
    }

    @ApiOperation("删除单个商品")
    @DeleteMapping("/deleteProById")
    public Result deleteProByIds(String id) {
        if (productService.removeById(id)) return Result.ok().message("删除成功");
        return Result.fail().message("删除失败");
    }

    @ApiOperation("获取所有的商品信息")
    @GetMapping("/getAllProduct")
    public Result getAllProduct() {
        return Result.ok(productService.list(null));
    }

    //    根据商品id,获取商品详细信息
    @ApiOperation("根据商品id,获取商品详细信息")
    @GetMapping("/getProductById")
    public Result getProductById(String id) {
        if (StringUtils.isEmpty(id)) return Result.fail().message("空数据");
        return Result.ok(productService.getProductById(id));
    }

    //    根据商品分类id获取首页展示的商品信息
    @ApiOperation("根据商品分类名称获取首页展示的商品信息")
    @RequestMapping("/getPromoProduct")
    public Result getCarousePic(String categoryName) {
        if (StringUtils.isEmpty(categoryName)) return Result.fail().message("空数据");

        return Result.ok(
                productService.getCarousePic(
                        categoryService.getCategoryByName(categoryName).getCategoryId().toString()
                )
        );
    }

    @ApiOperation("获取热门商品")
    @RequestMapping("/getHotProduct")
    public Result getHotProduct(@RequestBody List<String> categoryName) {
        System.out.println(categoryName.size());
        List<List<Product>> ans = new ArrayList<>();
        for (String s : categoryName) {
            ans.add(productService.getCarousePic(
                    categoryService.getCategoryByName(s).getCategoryId().toString()
            ));
        }
        return Result.ok(ans);
    }

    //    分页获取所有的商品信息
    @ApiOperation("分页获取所有的商品信息")
    @GetMapping("/getProductPage")
    public Result getProductPage(
            @ApiParam("查询页码") Integer pageNo,
            @ApiParam("页面大小") Integer pageSize) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        return Result.ok(productService.getProductPage(page, null));

    }

    //    根据商品分类id,分页获取商品信息
    @ApiOperation("根据商品分类id,分页获取商品信息")
    @GetMapping("/getProductPageBycategory")
    public Result getProductPageBycategory(Integer pageNo,
                                           Integer pageSize,
                                           @ApiParam("分类id") String categoryID) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        Product product = new Product();
        product.setCategoryId(Integer.valueOf(categoryID));
        return Result.ok(productService.getProductPage(page, product));
    }

    //    根据搜索条件,分页获取商品信息
    @ApiOperation("分类筛选商品,分页获取商品信息")
    @GetMapping("/getPages")
    public Result getPages(Integer pageNo,
                           Integer pageSize,
                           @ApiParam("分类id") Product product) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        return Result.ok(productService.getProductPage(page, product));
    }

    @ApiOperation("搜索商品")
    @GetMapping("/getProductBySearch")
    public Result getProductBySearch(Integer pageNo,
                                     Integer pageSize,
                                     String search) {
        Page<Product> page = new Page<>(pageNo, pageSize);
        Product product = new Product();
        product.setProductName(search);
        return Result.ok(productService.getProductPage(page, product));
    }
}


