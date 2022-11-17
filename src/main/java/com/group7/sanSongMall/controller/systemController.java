/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-17 10:37
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.util.CreateVerifyCodeImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api(tags = "系统控制器")
@RestController
@RequestMapping("/sysController")
public class systemController {

    /**
     * 获取验证码图片
     *
     * @param request  请求
     * @param response 响应
     */
    @ApiOperation("获取验证码图片")
    @GetMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response){
        /*
          获取验证码图片
         */
        BufferedImage verifyCodeImage = CreateVerifyCodeImage.getVerifyCodeImage();
        /*
          验证码
         */
        String verifyCode = String.valueOf(CreateVerifyCodeImage.getVerifyCode());
        System.out.println(verifyCode);
        /*
          图片传输到前端
         */
        request.getSession().setAttribute("verifyCode",verifyCode);
        try {
            ImageIO.write(verifyCodeImage,"JPEG",response.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }





}
