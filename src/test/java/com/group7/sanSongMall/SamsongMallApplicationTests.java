package com.group7.sanSongMall;

import com.group7.sanSongMall.util.BcryptCipher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class SamsongMallApplicationTests {

    @Test
    void contextLoads() {
        Map<String, String> juanxincai = BcryptCipher.Bcrypt("juanxincai");
        System.out.println(juanxincai.keySet());
        System.out.println(juanxincai.get("cipher"));

    }


}
