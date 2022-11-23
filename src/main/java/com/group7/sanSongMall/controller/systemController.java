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
import com.group7.sanSongMall.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private RedisTemplate<String, String> redisTemplate;

    private final static String verifyId = "verifyCode"+CPUSerialNumber.getCPUSerialNumber();
    /**
     * 获取验证码图片
     *
     * @param request  请求
     * @param response 响应
     */
    @ApiOperation("获取验证码图片")
    @ResponseBody
    @GetMapping("/getVerifyCodeImage")
    public Result getVerifyCodeImage(HttpServletRequest request,HttpServletResponse response) throws IOException {

//        response.setDateHeader("Expires", 0);
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        response.setHeader("Pragma", "no-cache");
//        response.setContentType("image/jpeg");
//            使用时间戳生成唯一验证码

        //生成验证码
        BufferedImage verifyCodeImage = CreateVerifyCodeImage.getVerifyCodeImage();
        String capText = String.valueOf(CreateVerifyCodeImage.getVerifyCode());
        //如果存在就先删除
        deleteKV(verifyId);

        //将验证码存储到redis数据库中
        redisTemplate.opsForValue().set(verifyId,capText,60, TimeUnit.SECONDS );
        //向客户端写出

        try {
            ImageIO.write(verifyCodeImage,"JPEG",response.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(capText);

        return Result.ok(capText);
    }


    @ApiOperation(value = "登录")
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm,HttpServletRequest request)  {
//        Cookie[] cookies = request.getCookies();
        Result login = userService.login(new User(loginForm.getAccount(),loginForm.getPassword()));
        System.out.println(loginForm);
        if (login.getData() == null) {
            return login;
        }
        // 获取系统正确的验证码
//        redis方法 session出错时候使用
        String verifyCode = redisTemplate.opsForValue().get("verifyCode"+CPUSerialNumber.getCPUSerialNumber());
        System.out.println("得到redis里面的" + verifyCode);
        if (StringUtils.isEmpty(verifyCode)){
            return Result.fail().message("验证码失效，请重试");
        }
        if (!verifyCode.equalsIgnoreCase(loginForm.getVerifyCode())){
            return Result.fail().message("验证码有误");
        }
        //验证完成之后立马删除
        deleteKV(verifyId);
        User userinfo = (User) login.getData();
        String token= TokenUtils.sign(userinfo);
        redisTemplate.opsForValue().set("token",token,10, TimeUnit.HOURS );
        return Result.ok(token);
    }

    @ApiOperation(value = "注册")
    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if (!StringUtils.isEmpty(user.getPassword())){
//            user.setPassword(BcryptCipher.Bcrypt(user.getPassword()).get(BcryptCipher.CIPHER_KEY));
            user.setPassword(Encode_MD5.encrypt(user.getPassword()));
        }
        if (StringUtils.isEmpty(user.getUsername())){
            user.setUsername("user_" + RandomStringUtils.randomNumeric(10));
        }
        //删除状态
        user.setIsDel(1);
        //角色
        user.setRole(1);
        //注册用户
        return userService.register(user);

    }

    @ApiOperation(value = "获取登录用户的信息")
    @GetMapping("/info")
    public Result getInfo(){
        String token = redisTemplate.opsForValue().get("token");
        if (StringUtils.isEmpty(token)){
            return Result.fail().message("请重新登录").code(202);
        }
        String userAccount = TokenUtils.getUserAccount(token);
        return userService.findUserMsgByAccount(new User(userAccount));
    }

    /**
     * 退出 销毁token
     *
     * @return {@link Result}
     */
    @ApiOperation(value = "退出")
    @GetMapping("/exit")
    public Result exit(){
        Set<String> token = redisTemplate.keys("token");
        redisTemplate.delete(token);
        return Result.ok();
    }

    @GetMapping("/getVerifyId")
    public Result getVerifyId(){
        return Result.ok(verifyId);
    }


    public void deleteKV(String name){
        Set<String> deleteCode = redisTemplate.keys(name);
        if (deleteCode!=null){
            redisTemplate.delete(deleteCode);
        }
    }
}
