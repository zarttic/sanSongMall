/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-26 22:02
 */

package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.Locations;

import java.util.List;

public interface LocationService extends IService<Locations> {

    List<Locations> getLocationById(String userId);
    int addLocation(Locations locations);
}
