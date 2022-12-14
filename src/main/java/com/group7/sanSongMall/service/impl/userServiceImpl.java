/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-18 11:35
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.User;
import com.group7.sanSongMall.mapper.userMapper;
import com.group7.sanSongMall.service.userService;
import com.group7.sanSongMall.util.Encode_MD5;
import com.group7.sanSongMall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("/userServiceImpl")
@Transactional
public class userServiceImpl extends ServiceImpl<userMapper, User> implements userService {

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link Result}
     */
    @Override
    public Result register(User user) {
        QueryWrapper<User> qw =  new QueryWrapper<>();
        qw.eq("account", user.getAccount());
        if (findUserMsgByAccount(user) != null){
            return Result.fail().message("此账号已被注册");
        }
        baseMapper.insert(user);
        return Result.ok().message("注册成功");
    }

    /**
     * 登录
     *
     * @param user 用户
     * @return {@link Result}
     */
    @Override
    public Result login(User user) {
        User userinfo = findUserMsgByAccount(user);
        if (userinfo == null){
            return Result.fail().code(207).message("查无此账号");
        }
        if(!Encode_MD5.encrypt(user.getPassword()).equals(userinfo.getPassword())){
            System.out.println(Encode_MD5.encrypt(user.getPassword()));
            return Result.fail().code(207).message("密码错误");
        }
        return Result.ok(userinfo);
    }

    /**
     * 使用用户账号得到所有的信息 用来校验
     *
     * @param user 用户
     * @return {@link Result}
     */
    @Override
    public User findUserMsgByAccount(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount());
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> getUserPage(Page<User> page, String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(account)) {
            queryWrapper.like("account", account);
        }
//        queryWrapper.orderByDesc("id");
        return baseMapper.selectPage(page, queryWrapper);
    }
}
