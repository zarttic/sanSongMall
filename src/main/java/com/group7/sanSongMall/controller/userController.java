/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-16 21:49
 */

package com.group7.sanSongMall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sanSongMall.entity.User;
import com.group7.sanSongMall.service.userService;
import com.group7.sanSongMall.util.Encode_MD5;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/userController")
public class userController {
    @Autowired
    private userService userService;

    /**
     * 分页获取用户信息
     *
     * @param pageNo   页码
     * @param pageSize 页面大小
     * @param account  账户
     * @return {@link Result}
     */
    @ApiOperation("分页查询用户信息")
    @GetMapping("/getUserPage")
    public Result getUserPage(Integer pageNo,
                              Integer pageSize,
                              String account) {
        Page<User> page = new Page<>(pageNo, pageSize);
        return Result.ok(userService.getUserPage(page, account));
    }

    /**
     * 根据id删除用户 可以批量
     *
     * @param ids id
     * @return {@link Result}
     */
    @ApiOperation("批量删除用户")
    @DeleteMapping("/delUserByIds")
    public Result delUserById(@RequestBody List<Integer> ids) {
        System.out.println("要删除的1"+ids);
        userService.removeByIds(ids);
        return Result.ok().message("删除了" + ids.size() + "条数据");
    }

    @ApiOperation("删除单个用户")
    @DeleteMapping("/delUserById")
    public Result delUserById(String id) {
        System.out.println("要删除的2"+id);
        userService.removeById(id);
        return Result.ok().message("删除了" + 1 + "条数据");
    }

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return {@link Result}
     */
    @ApiOperation("更新用户")

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        System.out.println("上user" + user);
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(Encode_MD5.encrypt(user.getPassword()));
        }
        System.out.println("修改处user" + user);
        userService.updateById(user);
        return Result.ok();
    }
}
