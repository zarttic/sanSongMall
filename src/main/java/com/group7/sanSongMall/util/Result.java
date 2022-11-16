/**
 * {@code @Description} 状态体
 *
 * @author liyajun
 * {@code @create}          2022-11-16 21:56
 */

package com.group7.sanSongMall.util;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {
    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码")
    private Integer code;

    /**
     * 返回消息
     */
    @ApiModelProperty(value = "返回消息")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 空构造函数
     */
    public Result(){}

    /**
     * 构建
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> build(T data){
        Result<T> res = new Result<T>();
        if (data != null)res.setData(data);
        return res;
    }

    /**
     * 构建
     *
     * @param data           数据
     * @param resultCodeEnum 枚举结果代码
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 成功  无数据
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(){
        return Result.ok(null);
    }
    /**
     * 成功
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data,ResultCodeEnum.SUCCESS);
    }

    /**
     * 失败 无数据
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(){
        return Result.fail(null);
    }
    /**
     * 操作失败
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data,ResultCodeEnum.FAIL);
    }
    /**
     * 设置消息
     *
     * @param msg 消息
     * @return {@link Result}<{@link T}>
     */
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    /**
     * 设置响应码
     *
     * @param code 代码
     * @return {@link Result}<{@link T}>
     */
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
    /**
     * 是否成功
     *
     * @return boolean
     */
    public boolean isOK(){
        return this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue();
    }

}
