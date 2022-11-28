/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-28 16:28
 */

package com.group7.sanSongMall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;

}
