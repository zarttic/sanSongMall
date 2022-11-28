/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-28 16:39
 */

package com.group7.sanSongMall.entity;

import lombok.Data;

@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;
}
