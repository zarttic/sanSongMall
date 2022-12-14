/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 20:21
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.Carousel;
import com.group7.sanSongMall.mapper.carouselMapper;
import com.group7.sanSongMall.service.carouselService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/carouselServiceImpl")
@Transactional
public class carouselServiceImpl extends ServiceImpl<carouselMapper, Carousel> implements carouselService {
    //获取轮播图数据
    @Override
    public List<Carousel> getPic() {
        return baseMapper.selectList(null);
    }
}
