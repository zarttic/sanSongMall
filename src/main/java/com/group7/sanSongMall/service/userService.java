/**
 * @Description
 * @author liyajun
 * @create 2022-11-18 11:35
 */


package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.User;
import com.group7.sanSongMall.util.Result;

public interface userService extends IService<User> {
    /**
     * 注册
     *
     * @param user 用户
     * @return {@link Result}
     */
    public Result register(User user);

    /**
     * 登录
     *
     * @param user 用户
     * @return {@link Result}
     */
    public Result login(User user);

    /**
     * 使用用户账号得到所有的信息
     *
     * @param user 用户
     * @return {@link User}
     */
    public User findUserMsgByAccount(User user);


    IPage<User> getUserPage(Page<User> page, String account);
}
