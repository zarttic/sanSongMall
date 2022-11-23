/**
 * {@code @Description}种类管理
 *  接口已测试 2022-11-22
 * @author liyajun
 * {@code @create}          2022-11-21 19:17
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.service.categoryService;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "种类管理")
@RestController
@RequestMapping("/categoryController")
public class categoryController {
    @Autowired
    private categoryService categoryService;

    // 连接数据库获取商品分类
    @ApiOperation("获取所有的商品分类")
    @GetMapping("/getCategories")
    public Result getCategories(){
        return Result.ok(categoryService.getCategories());
    }

    // 连接数据库根据商品分类名称获取分类id
    @ApiOperation("根据分类名称获得分类id")
    @GetMapping("/getCategoryByName")
    public Result getCategoryByName(String categoryName){
        if (StringUtils.isEmpty(categoryName))return Result.fail().message("空请求");
        return Result.ok(categoryService.getCategoryByName(categoryName));
    }
//    分页获取所有的商品信息
//    @ApiOperation("分页获取所有商品信息")
//    根据商品分类id获取首页展示的商品信息

}
