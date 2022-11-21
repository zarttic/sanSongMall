/**
 * {@code @Description}
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "收藏管理")
@RestController
@RequestMapping("/collectController")
public class collectController {
    @Autowired
    com.group7.sanSongMall.service.collectService collectService;
//    把收藏商品信息插入数据库
    @ApiOperation("把收藏商品信息插入数据库")
    @GetMapping("/addCollect")
    public Result addCollect(@RequestBody Collect collect){
        return collectService.addCollect(collect);
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
    public Result getOneCollect(@RequestBody Collect collect){
        return  collectService.getOneCollect(collect);
    }

//    删除用户的某个收藏商品信息
    @ApiOperation("删除用户的某个收藏商品信息")
    @GetMapping("/delCollect")
    public Result delCollect(@RequestBody Collect collect){
        return collectService.delCollect(collect);
    }
}



