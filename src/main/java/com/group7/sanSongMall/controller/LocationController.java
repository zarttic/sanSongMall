/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-26 22:01
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.entity.Locations;
import com.group7.sanSongMall.service.LocationService;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "地址管理")
@RestController
@RequestMapping("/LocationController")
public class LocationController {

    @Autowired
    LocationService locationService;
    @ApiOperation("根据用户id获取所有的地址")
    @GetMapping("/getLocationById")
    public Result getLocationById(String userId) {
        if (StringUtils.isEmpty(userId))return Result.fail().message("没有传入用户信息");
        return Result.ok(locationService.getLocationById(userId));
    }

    @ApiOperation("新增地址")
    @PostMapping("/addLocation")
    public Result addLocation(@RequestBody Locations locations) {
        if (locations == null || locations.getUserId() == null) return Result.fail().message("传入空数据");
        List<Locations> locationsById = locationService.getLocationById(locations.getUserId().toString());
        //若是存在两个相同的话就
        for (Locations locations1 : locationsById) {
            if (check(locations1, locations)){
                return Result.fail().message("存在两个相同的地址");
            }
        }

        //不存在就插入
        if (locationService.save(locations))return Result.ok().message("新增地址成功");
        return Result.fail().message("未知原因 新增失败 ");
    }


    @ApiOperation("更新地址")
    @PostMapping("/updateLocation")
    public Result updateLocation(@RequestBody Locations locations){
        System.out.println(locations);
        //新增
        if (locations == null) {
            return Result.fail().message("空数据");
        }//新增
        else if (locations.getId() == null) {
            return addLocation(locations);
        }
        //更新
        //需要传入id
        if (locationService.updateById(locations)) return Result.ok().message("成功");
        return Result.fail().message("更新失败 请重试");
    }

    @ApiOperation("删除地址")
    @GetMapping("/deleteLocation")
    public Result deleteLocation(String id){
        if (StringUtils.isEmpty(id))return Result.fail().message("空信息");
        if (locationService.removeById(id)) return Result.ok().message("删除成功");
        return Result.fail().message("删除失败");
    }

    public boolean check(Locations l1, Locations l2){
        return l1.getUserId().equals(l2.getUserId()) &&
                l1.getUsername().equals(l2.getUsername()) &&
                l1.getPhone().equals(l2.getPhone()) &&
                l1.getLocation().equals(l2.getLocation()) &&
                l1.getTabs().equals(l2.getTabs());
    }
}
