/**
 * @Description
 * @author liyajun
 * @create 2022-11-21 15:53
 */


package com.group7.sanSongMall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.sanSongMall.entity.orders;
import com.group7.sanSongMall.util.Result;

import java.util.List;

public interface ordersService extends IService<orders> {

    /**
     * 分页查询订单 可加参数 user_id order_id product_id
     *
     * @param page   页面
     * @param orders 订单
     * @return {@link IPage}<{@link orders}>
     */
    IPage<orders> getOrdersPage(Page<orders> page, orders orders);


    /**
     * 通过id获取订单
     *
     * @param id id
     * @return {@link Result}
     */
    List<orders> getOrdersById(String id);

    orders getOrderByOrderId(String orderId);

}
