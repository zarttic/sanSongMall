/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-26 22:02
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.Locations;
import com.group7.sanSongMall.mapper.LocationMapper;
import com.group7.sanSongMall.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/locationServiceImpl")
@Transactional
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Locations> implements LocationService {
    @Override
    public List<Locations> getLocationById(String userId) {
        QueryWrapper<Locations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int addLocation(Locations locations) {
        QueryWrapper<Locations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", locations.getUserId());
        queryWrapper.eq("username", locations.getUsername());
        queryWrapper.eq("phone", locations.getPhone());
        queryWrapper.eq("location", locations.getLocation());
        Locations locations1 = baseMapper.selectOne(queryWrapper);
        if (locations1 != null){

        }
        return 0;
    }
}
