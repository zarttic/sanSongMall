/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 19:14
 */

package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Category;

import java.util.List;

public interface categoryService extends IService<Category> {
    //查询所有的类别
    List<Category> getCategories();
    //根据名称获取id
    Category getCategoryByName(String categoryName);
}
