/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:34
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.group7.sanSongMall.service.productpicService;

@Api(tags = "商品图片管理")
@RestController
@RequestMapping("/productpicController")
public class productpicController {
    @Autowired
    private productpicService productpicService;

    //    根据商品id,获取商品图片
    @ApiOperation("根据商品id,获取商品图片")
    @GetMapping("/getProductPic")
    public Result getProductPic(String id) {
        return productpicService.getProductPic(id);
    }
}

