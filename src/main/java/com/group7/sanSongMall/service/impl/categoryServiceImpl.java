/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 19:14
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.Category;
import com.group7.sanSongMall.mapper.categoryMapper;
import com.group7.sanSongMall.service.categoryService;
import com.group7.sanSongMall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("/categoryServiceImpl")
@Transactional
public class categoryServiceImpl extends ServiceImpl<categoryMapper, Category> implements categoryService {
    //查询所有的类别
    @Override
    public Result getCategories() {
        return Result.ok(baseMapper.selectList(null));
    }

    //根据名称获取id
    @Override
    public Result getCategoryByName(String categoryName) {
        if (StringUtils.isEmpty(categoryName))return Result.fail().message("错误请求");
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", categoryName);
        return Result.ok(baseMapper.selectOne(queryWrapper));
    }
}
