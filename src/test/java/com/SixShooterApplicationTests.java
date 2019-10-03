package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SixShooterApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        boolean bl = stringRedisTemplate.hasKey("1040230345@qq.com");
        System.out.println(bl);
        String st = stringRedisTemplate.opsForValue().get("asdasd");
        System.out.println(st);
    }

}
