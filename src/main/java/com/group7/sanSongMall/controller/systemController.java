/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-17 10:37
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.entity.LoginForm;
import com.group7.sanSongMall.entity.User;
import com.group7.sanSongMall.service.userService;
import com.group7.sanSongMall.util.CreateVerifyCodeImage;
import com.group7.sanSongMall.util.Encode_MD5;
import com.group7.sanSongMall.util.Result;
import com.group7.sanSongMall.util.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Api(tags = "系统管理")
@RestController
@RequestMapping("/sysController")
public class systemController {

    @Autowired
    private userService userService;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 获取验证码图片
     *
     * @param request  请求
     * @param response 响应
     */
    @ApiOperation("获取验证码图片")
    @ResponseBody
    @GetMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        BufferedImage verifyCodeImage = CreateVerifyCodeImage.getVerifyCodeImage();
        String capText = String.valueOf(CreateVerifyCodeImage.getVerifyCode());
        session.setAttribute("verifyCode", capText);
        System.out.println(session.getAttribute("verifyCode"));
        //将验证码存储到redis数据库中
        redisTemplate.opsForValue().set("verifyCode",capText,60, TimeUnit.SECONDS );
        //向客户端写出

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(verifyCodeImage, "jpeg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }


//        /*
//          获取验证码图片
//         */
//        BufferedImage verifyCodeImage = CreateVerifyCodeImage.getVerifyCodeImage();
//        /*
//          验证码
//         */
//        String verifyCode = String.valueOf(CreateVerifyCodeImage.getVerifyCode());
//        /*
//        测试输出验证码
//         */
//        System.out.println(verifyCode);
//        Cookie c = new Cookie("verifyCode",verifyCode);
//        response.addCookie(c);
//        /*
//          图片传输到前端
//         */
//        request.getSession().setAttribute("verifyCode",verifyCode);
//        //设置响应头
//        response.setHeader("Pragma", "no-cache");
//        //设置响应头
//        response.setHeader("Cache-Control", "no-cache");
//        //在代理服务器端防止缓冲
//        response.setDateHeader("Expires", 0);
//
//        System.out.println("获取验证码处session" + request.getSession());
//
//        System.out.println("getAttribute -> " + request.getSession().getAttribute("verifyCode"));
//        try {
//            ImageIO.write(verifyCodeImage,"JPEG",response.getOutputStream());
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }


    @ApiOperation(value = "登录")
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm)  {
//        Cookie[] cookies = request.getCookies();
        Result login = userService.login(new User(loginForm.getAccount(),loginForm.getPassword()));
        System.out.println(loginForm);
        if (login.getData() == null) {
            return login;
        }
        // 获取系统正确的验证码
        String verifyCode = (String) redisTemplate.opsForValue().get("verifyCode");
        System.out.println("得到redis里面的"+verifyCode);
        if (StringUtils.isEmpty(verifyCode)){
            return Result.fail().message("验证码失效，请重试");
        }
        if (!verifyCode.equalsIgnoreCase(loginForm.getVerifyCode())){
            return Result.fail().message("验证码有误");
        }

        User userinfo = (User) login.getData();
        String token= TokenUtils.sign(userinfo);
        redisTemplate.opsForValue().set("token",token,10, TimeUnit.HOURS );
        return Result.ok(token);
    }

    @ApiOperation(value = "注册")
    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestBody User user){

        System.out.println(user);

        if (!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(Encode_MD5.encrypt(user.getPassword()));
        }

        user.setIsDel(1);

        user.setRole(1);

        userService.register(user);

        return Result.ok();
    }

    @GetMapping("/info")
    public Result getInfo(){
        String token =  (String) redisTemplate.opsForValue().get("token");
        if (StringUtils.isEmpty(token)){
            return Result.fail().message("请重新登录").code(202);
        }
        String userAccount = TokenUtils.getUserAccount(token);
        return userService.findUserMsgByAccount(new User(userAccount));
    }

    @GetMapping("/exit")
    public Result exit(){
        Set token = redisTemplate.keys("token");
        redisTemplate.delete(token);
        return Result.ok();
    }


}
