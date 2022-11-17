/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-16 21:49
 */

package com.group7.sanSongMall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group7.sanSongMall.entity.User;
import com.group7.sanSongMall.util.Result;
import com.group7.sanSongMall.util.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userController")
public class userController {
    @GetMapping("/test")
    public String test(){
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        return "Spring版本:"+springVersion+"\nSpringBoot版本:"+springBootVersion;
    }

    @ApiOperation(value = "登录")
    @GetMapping("/login")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "String"),
//            @ApiImplicitParam(name = "passWord", value = "密码", paramType= "String")
//    })
    public Result login(@RequestParam(value = "userName") String userName, @RequestParam(value = "passWord") String passWord) throws JsonProcessingException {
        //包装token
        User user = new User();
        user.setUsername(userName);
        user.setPassword(passWord);
        String token= TokenUtils.sign(user);
        return Result.ok(token);
    }
}
