/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 15:54
 */

package com.group7.sanSongMall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.sanSongMall.entity.orders;
import com.group7.sanSongMall.mapper.ordersMapper;
import com.group7.sanSongMall.service.ordersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("/ordersServiceImpl")
@Transactional
public class ordersServiceImpl extends ServiceImpl<ordersMapper, orders> implements ordersService {
    /**
     * 分页查询订单 可加参数 user_id order_id product_id
     *
     * @param page   页面
     * @param orders 订单
     * @return {@link IPage}<{@link orders}>
     */
    @Override
    public IPage<orders> getOrdersPage(Page<orders> page, orders orders) {
        QueryWrapper<orders> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(orders.getUserId()))queryWrapper.eq("user_id", orders.getUserId());
        if(!StringUtils.isEmpty(orders.getOrderId()))queryWrapper.eq("order_id", orders.getOrderId());
        if(!StringUtils.isEmpty(orders.getProductId()))queryWrapper.eq("product_id", orders.getProductId());
        queryWrapper.orderByDesc("id");
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<orders> getOrdersById(String id) {
        System.out.println(id);

        QueryWrapper<orders> queryWrapper = new
                QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public orders getOrderByOrderId(String orderId) {
        QueryWrapper<orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return baseMapper.selectOne(queryWrapper);
    }

}
