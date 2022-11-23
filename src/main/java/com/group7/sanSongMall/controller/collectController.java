/**
 * {@code @Description}
 * 已完成接口测试 2022-11-22
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:30
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.entity.Collect;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "收藏管理")
@RestController
@RequestMapping("/collectController")
public class collectController {
    @Autowired
    com.group7.sanSongMall.service.collectService collectService;
    @Autowired
    com.group7.sanSongMall.service.productService productService;

    //    把收藏商品信息插入数据库
    @ApiOperation("把收藏商品信息插入数据库")
    @PostMapping("/addCollect")
    public Result addCollect(String userId, String productId) {
        if (!collectService.getOneCollect(userId, productId).isEmpty()) return Result.fail().message("此商品已添加");
        return Result.ok(collectService.addCollect(userId, productId));
    }

    //    获取用户的所有收藏商品信息
    @ApiOperation(" 获取用户的所有收藏商品信息")
    @GetMapping("/getCollects")
    public Result getCollects(String userId) {
        List<Collect> collects = collectService.getCollects(userId);
        return Result.ok(productService.listByIds(
                collects.stream().map(
                        Collect::getProductId).collect(
                        Collectors.toList())));
    }

    //    获取用户的某个收藏商品信息
    @ApiOperation("获取用户的某个收藏商品信息")
    @GetMapping("/getOneCollect")
    public Result getOneCollect(String userId, String productId) {
        return Result.ok(collectService.getOneCollect(userId, productId));
    }

    //    删除用户的某个收藏商品信息
    @ApiOperation("删除用户的某个收藏商品信息")
    @DeleteMapping("/delCollect")
    public Result delCollect(String userId, String productId) {
        if (collectService.delCollect(userId, productId) > 0)return Result.ok().message("删除成功");
        return Result.fail().message("删除失败 请刷新重试");
    }
}



