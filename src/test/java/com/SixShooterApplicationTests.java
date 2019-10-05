package com;

import com.dto.UserDto;
import com.my_util.PlayFair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SixShooterApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PlayFair playFair;

    @Test
    public void contextLoads() {
        System.out.println(playFair.begin_Play());


    }

}
