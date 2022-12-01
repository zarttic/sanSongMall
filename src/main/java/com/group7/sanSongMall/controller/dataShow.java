/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-30 22:19
 */

package com.group7.sanSongMall.controller;

import com.group7.sanSongMall.entity.orders;
import com.group7.sanSongMall.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Api(tags = "数据展示")
@RestController
@RequestMapping("/dataShow")
public class dataShow {

    @Autowired
    private com.group7.sanSongMall.service.ordersService ordersService;
    /**
     * 获取数据
     * 1. 先获取所有的订单数据
     * 2. 筛选出来有用的数据
     * 3. 分类计算出来日期   ？ 近五日 若是没又成交额就就默认为0
     * 4. 计算出来总销售额
     * @return {@link Result}
     */
    @ApiOperation("前端数据渲染")
    @GetMapping("/getData")
    public Result getData(){
        //获取所有的订单
        List<orders> list = ordersService.list();
        //筛选出来所有有效的 订单支付成功的为有效
        List<orders> collect = list.stream().filter(orders -> (orders.getState() == 1)).collect(Collectors.toList());
        //日期筛选
        Map<String,Double> mp = new TreeMap<>();
        for (orders orders : collect) {
            String cur  = getMonAndDay(orders.getOrderTime().toString());
            mp.put(cur,mp.getOrDefault(cur,0.0) + orders.getProductPrice());
        }

        return Result.ok(mp);
    }
    public String getMonAndDay(String time){
        String[] s = time.split(" ");
        return s[0];
    }
}
