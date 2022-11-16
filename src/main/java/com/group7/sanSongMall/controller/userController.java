/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-16 21:49
 */

package com.group7.sanSongMall.controller;

import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
    @GetMapping("/test")
    public String test(){
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        return "Spring版本:"+springVersion+"\nSpringBoot版本:"+springBootVersion;
    }

}
