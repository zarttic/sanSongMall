/**
 * {@code @Description}
 * 接口已测试 2022-11-22
 *
 * @author liyajun
 * {@code @create}          2022-11-21 15:59
 */

package com.group7.sanSongMall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sanSongMall.controller.dto.orderDTO;
import com.group7.sanSongMall.entity.Product;
import com.group7.sanSongMall.entity.Shoppingcart;
import com.group7.sanSongMall.entity.orders;
import com.group7.sanSongMall.service.ordersService;
import com.group7.sanSongMall.service.productService;
import com.group7.sanSongMall.util.Result;
import com.group7.sanSongMall.util.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(tags = "订单管理")
@RestController
@RequestMapping("/ordersController")
public class ordersController {
    @Autowired
    private ordersService ordersService;

    @Autowired
    private com.group7.sanSongMall.service.shopcarService shopcarService;
    @Autowired
    productService productService;

    @ApiOperation("将订单设置为失效状态")
    @GetMapping("/setExpired")
    public Result setExpired(String orderId){
        List<orders> orderByOrderId = ordersService.getOrderByOrderId(orderId);
        for (int i = 0; i < orderByOrderId.size(); i++) {
            orderByOrderId.get(i).setDelay(0L);
            orderByOrderId.get(i).setState(-1);
            ordersService.updateById(orderByOrderId.get(i));
        }
        return Result.ok();
    }

    /**
     * 分页查询信息 带条件 管理员管理的时候使用
     * 注意事项 就算orders参数没有也要传入一个空值
     *
     * @param pageNo   页面编号
     * @param pageSize 页面大小
     * @param orders   订单
     * @return {@link Result}
     */
    @ApiOperation("分页查询订单信息")
    @GetMapping("/getOrdersPage/{pageNo}/{pageSize}")
    public Result getOrdersPage(
            @ApiParam("查询页码") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页面大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("账户模糊查询") @RequestBody orders orders) {
        Page<orders> page = new Page<>(pageNo, pageSize);
        return Result.ok(ordersService.getOrdersPage(page, orders));
    }

    /**
     * 根据id删除订单
     *
     * @param ids id
     * @return {@link Result}
     */
    @ApiOperation("根据id删除订单")
    @DeleteMapping("/delOrderByIds")
    public Result delOrderByIds(@RequestBody List<Integer> ids) {
        System.out.println(ids);
        ordersService.removeByIds(ids);
        return Result.ok().message("移除了" + ids.size() + "条信息");
    }

    /**
     * 添加订单
     * 添加或者修改订单
     * 需要删除购物车之后才能添加订单
     * 先获取所有的购物车订单
     * 插入到
     *
     * @param userId 用户id
     * @return {@link Result}
     */
    @ApiOperation("新增订单")
    @GetMapping("/addOrders")
    public Result addOrders(String userId,String locationId,String productIds) {
        System.out.println(userId);
        System.out.println(locationId);
        System.out.println(productIds);
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(productIds) || StringUtils.isEmpty(locationId)) return Result.fail().message("空数据");
        List<Shoppingcart> shopcar = shopcarService.getshopcar(userId);
        List<String> strings = Arrays.stream(productIds.split(",")).collect(Collectors.toList());
        //生成唯一订单id
        SnowFlake idWorker = new SnowFlake(0, 0);
        long l = idWorker.nextId();
        String tem = String.valueOf(l);
        System.out.println("生成的密钥为" + l);
        //遍历所有的订单
        for (Shoppingcart shoppingcart : shopcar) {
            for (String productId : strings) {
                if (productId.equals(shoppingcart.getProductId().toString())) {
                    orders orders = new orders();
                    orders.setOrderId(tem);
                    orders.setUserId(Integer.valueOf(userId));
                    orders.setProductId(shoppingcart.getProductId());
                    orders.setProductNum(shoppingcart.getNum());
                    orders.setLocation(locationId);
                    orders.setState(0);
                    //为秒数 默认为两小时
                    Long delay = System.currentTimeMillis() + 7200000;
                    orders.setDelay(delay);
                    Product data = productService.getProductById(String.valueOf(shoppingcart.getProductId()));
                    //使用实际销售价格计算
//                    orders.setProductPrice(shoppingcart.getNum() * Double.parseDouble(data.getProductSellingPrice()));
                    orders.setProductPrice( Double.parseDouble(data.getProductSellingPrice()));
                    ordersService.save(orders);
                }

            }

        }
        int cnt = 0;
        //开始删除购物车里面的内容
        for (String string : strings) {
            cnt+=shopcarService.deleteShoppingCart(userId, string);
        }

        System.out.println("生成的密钥为" + tem);
        if (cnt == strings.size()) return Result.ok(tem).message("创建订单成功");
        return Result.fail().message("创建订单失败");
    }

    /**
     * 通过id获取订单
     * 传入用户id 来获取信息
     * 先查出所有的order_id 然后 根据每个order_id聚合起来想对应的商品
     *
     * @param userId 用户id
     * @return {@link Result}
     */
    @ApiOperation("获取当前用户所有的订单信息")
    @GetMapping("/getOrdersById")
    public Result getOrdersById(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Result.fail().message("请刷新重试");
        }
        //先查出所有的订单信息
        List<orders> order = ordersService.getOrdersById(userId);
        //获取所有的订单
        List<String> ids = order.stream().map(orders::getOrderId).distinct().collect(Collectors.toList());
        List<List<orderDTO>> ans = new ArrayList<>();

        for (String id : ids) {
            //创建一个新的
            List<orderDTO> tem = new ArrayList<>();
            for (orders orders : order) {
                //订单id 订单时间 商品id  商品数量 商品价格
                //商品名称 商品图片
                if (Objects.equals(orders.getOrderId(), id)) {
                    orderDTO orderDTO = new orderDTO();
                    orderDTO.setOrders(orders);
                    Product productById = productService.getProductById(orders.getProductId().toString());
                    orderDTO.setProductPic(productById.getProductPicture());
                    orderDTO.setProductName(productById.getProductName());
                    tem.add(orderDTO);
                }
            }
            ans.add(tem);
        }
//        for (int i = 0; i < ids.size(); i++) {
//            //创建一个新的
//            List<orderDTO> tem = new ArrayList<>();
//            for (int j = 0; j < order.size(); j++) {
//                //订单id 订单时间 商品id  商品数量 商品价格
//                //商品名称 商品图片
//                if (Objects.equals(order.get(j).getOrderId(), ids.get(i))) {
//                    orderDTO orderDTO = new orderDTO();
//                    orderDTO.setOrders(order.get(j));
//                    Product productById = productService.getProductById(order.get(j).getProductId().toString());
//                    orderDTO.setProductPic(productById.getProductPicture());
//                    orderDTO.setProductName(productById.getProductName());
//                    tem.add(orderDTO);
//                }
//            }
//            ans.add(tem);
//        }
        return Result.ok(ans);


    }

}
