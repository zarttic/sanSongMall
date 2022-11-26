/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-26 22:02
 */

package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Location;

import java.util.List;

public interface LocationService extends IService<Location> {

    List<Location> getLocationById(String userId);
    int addLocation(Location location);
}
