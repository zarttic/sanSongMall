/**
 * {@code @Description}
 *  已完成接口测试 2022-11-22
 * @author liyajun
 * {@code @create}          2022-11-21 20:30
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "收藏管理")
@RestController
@RequestMapping("/collectController")
public class collectController {
    @Autowired
    com.group7.sanSongMall.service.collectService collectService;
//    把收藏商品信息插入数据库
    @ApiOperation("把收藏商品信息插入数据库")
    @PostMapping("/addCollect")
    public Result addCollect(String userId,String productId){
        System.out.println(userId + " _____   "+productId);
        return collectService.addCollect(userId,productId);
    }

//    获取用户的所有收藏商品信息
    @ApiOperation(" 获取用户的所有收藏商品信息")
    @GetMapping("/getCollects")
    public Result getCollects(){
        return collectService.getCollects();
    }

//    获取用户的某个收藏商品信息
    @ApiOperation("获取用户的某个收藏商品信息")
    @GetMapping("/getOneCollect")
    public Result getOneCollect(String userId,String productId){
        return  collectService.getOneCollect(userId,productId);
    }

//    删除用户的某个收藏商品信息
    @ApiOperation("删除用户的某个收藏商品信息")
    @DeleteMapping("/delCollect")
    public Result delCollect(String userId,String productId){
        return collectService.delCollect(userId,productId);
    }
}



