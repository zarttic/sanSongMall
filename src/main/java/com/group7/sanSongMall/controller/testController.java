/**
 * {@code @Description}   用于测试
 *
 * @author liyajun
 * {@code @create}          2022-11-18 12:52
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试")
@RestController
@RequestMapping("/test")
public class testController {
    @GetMapping("/test")
    public Result test(){
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        return Result.ok("Spring版本:"+springVersion+"\nSpringBoot版本:"+springBootVersion);
    }
}
