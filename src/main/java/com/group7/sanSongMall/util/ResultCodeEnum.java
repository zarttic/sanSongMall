/**
 * {@code @Description}  状态码
 *
 * @author liyajun
 * {@code @create}          2022-11-16 22:06
 */

package com.group7.sanSongMall.util;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    /**
     * @code 200
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * @code 201
     * 失败
     */
    FAIL(201, "失败"),
    /**
     * @code 202
     * 服务错误
     */
    SERVICE_ERROR(202, "服务异常"),
    /**
     * @code 204
     * 非法请求
     */
    ILLEGAL_REQUEST( 204, "非法请求"),
    /**
     * @code 205
     * 支付运行
     */
    PAY_RUN(205, "支付中"),
    /**
     * @code 206
     * 参数校验错误
     */
    ARGUMENT_VALID_ERROR(206, "参数校验错误"),

    /**
     * @code 207
     * 登录错误
     */
    LOGIN_ERROR(207, "用户名或密码错误"),
    /**
     * @code 208
     * 登录身份验证
     */
    LOGIN_AUTH(208, "未登陆"),
    /**
     * @code 209
     * 没有权限
     */
    PERMISSION(209, "没有权限"),
    /**
     * @code 210
     * 秒杀没有开始
     */
    SECKILL_NO_START(210, "秒杀还没开始"),
    /**
     * @code 211
     * 正在排队中
     */
    SECKILL_RUN(211, "正在排队中"),
    /**
     * @code 212
     * 您有未支付的订单
     */
    SECKILL_NO_PAY_ORDER(212, "您有未支付的订单"),
    /**
     * @code 213
     * 已售罄
     */
    SECKILL_FINISH(213, "已售罄"),
    /**
     * @code 214
     * 秒杀结束
     */
    SECKILL_END(214, "秒杀已结束"),
    /**
     * @code 215
     * 秒杀成功
     */
    SECKILL_SUCCESS(215, "抢单成功"),
    /**
     * @code 216
     * 秒杀失败
     */
    SECKILL_FAIL(216, "抢单失败"),
    /**
     * @code 217
     * 请求不合法
     */
    SECKILL_ILLEGAL(217, "请求不合法"),
    /**
     * @code 218
     * 秒杀订单成功
     */
    SECKILL_ORDER_SUCCESS(218, "下单成功"),
    /**
     * @code 220
     * 优惠券已经领取
     */
    COUPON_GET(220, "优惠券已经领取"),
    /**
     * @code 221
     * 优惠券已发放完毕
     */
    COUPON_LIMIT_GET(221, "优惠券已发放完毕"),

    /**
     * @code 222
     * 会话失效
     */
    LOGIN_CODE(222,"长时间未操作,会话已失效,请刷新页面后重试!"),
    /**
     * @code 223
     * 代码错误
     */
    CODE_ERROR(223,"验证码错误!"),
    /**
     * @code 224
     * 令牌错误
     */
    TOKEN_ERROR(224,"Token无效!");

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 响应体
     *
     * @param code    代码
     * @param message 消息
     */
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
