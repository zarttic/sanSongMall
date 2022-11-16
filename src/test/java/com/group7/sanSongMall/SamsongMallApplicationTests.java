package com.group7.sanSongMall;

import com.group7.sanSongMall.entity.TokenBody;
import com.group7.sanSongMall.util.Encode_MD5;
import com.group7.sanSongMall.util.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SamsongMallApplicationTests {

    @Test
    void contextLoads() {
        String jun = JwtHelper.createToken(1L, 1, "jun");
        System.out.println("校验码为"+jun);
        TokenBody tokenMessage = JwtHelper.getTokenMessage(jun);
        String hello = Encode_MD5.encrypt("hello");
        System.out.println("md5加密"+hello);
        System.out.println(tokenMessage);
    }

}
