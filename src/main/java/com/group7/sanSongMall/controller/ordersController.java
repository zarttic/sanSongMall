/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 15:59
 */

package com.group7.sanSongMall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sanSongMall.entity.orders;
import com.group7.sanSongMall.service.ordersService;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "订单管理")
@RestController
@RequestMapping("/ordersController")
public class ordersController {
    @Autowired
    private ordersService ordersService;


    /**
     * 分页查询信息 带条件 管理员管理的时候使用
     *
     * @param pageNo   页面没有
     * @param pageSize 页面大小
     * @param orders   订单
     * @return {@link Result}
     */
    @ApiOperation("分页查询订单信息")
    @GetMapping("/getOrdersPage/{pageNo}/{pageSize}")
    public Result getOrdersPage(
            @ApiParam("查询页码") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页面大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("账户模糊查询") @RequestBody orders orders){
        Page<orders> page = new Page<>(pageNo,pageSize);
        return Result.ok(ordersService.getOrdersPage(page,orders));
    }

    /**
     * 根据id删除订单
     * @param ids id
     * @return {@link Result}
     */
    @ApiOperation("根据id删除订单")
    @DeleteMapping("/delOrderByIds")
    public Result delOrderByIds(@RequestBody List<Integer> ids){
        ordersService.removeByIds(ids);
        return Result.ok().message("移除了"+ids.size()+"条信息");
    }

    /**
     * 添加或者修改订单
     *
     * @param orders 订单
     * @return {@link Result}
     */
    @ApiOperation("新增订单")
    @PostMapping("/addOrders")
    public Result addOrders(@RequestBody orders orders){
        ordersService.saveOrUpdate(orders);
        return Result.ok();
    }

    /**
     * 传入用户id 来获取信息
     *
     * @param id id
     * @return {@link Result}
     */
    @ApiOperation("获取当前用户所有的订单信息")
    @GetMapping("/getOrdersById")
    public Result getOrdersById(@RequestBody String id){
        return ordersService.getOrdersById(id);
    }

}
