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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/categoryServiceImpl")
@Transactional
public class categoryServiceImpl extends ServiceImpl<categoryMapper, Category> implements categoryService {
    //查询所有的类别
    @Override
    public List<Category> getCategories() {
        return baseMapper.selectList(null);
    }

    //根据名称获取id
    @Override
    public Category getCategoryByName(String categoryName) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", categoryName);
        return baseMapper.selectOne(queryWrapper);
    }
}
