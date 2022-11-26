/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-26 22:02
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.Location;
import com.group7.sanSongMall.mapper.LocationMapper;
import com.group7.sanSongMall.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/locationServiceImpl")
@Transactional
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements LocationService {
    @Override
    public List<Location> getLocationById(String userId) {
        QueryWrapper<Location> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int addLocation(Location location) {
        QueryWrapper<Location> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",location.getUserId());
        queryWrapper.eq("username",location.getUsername());
        queryWrapper.eq("phone",location.getPhone());
        queryWrapper.eq("location",location.getLocation());
        Location location1 = baseMapper.selectOne(queryWrapper);
        if (location1 != null){

        }
        return 0;
    }
}
