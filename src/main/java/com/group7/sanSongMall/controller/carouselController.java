/**
 * {@code @Description} 轮播图管理
 * 已完成测试 2022-11-22
 * @author liyajun
 * {@code @create}          2022-11-21 20:30
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.service.carouselService;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "轮播图管理")
@RestController
@RequestMapping("/carouselController")
public class carouselController {
    @Autowired
    carouselService carouselService;
    //获取轮播图数据
    @ApiOperation("获取轮播图数据")
    @GetMapping("/getPic")
    public Result getPic() {
        return carouselService.getPic();
    }
}
