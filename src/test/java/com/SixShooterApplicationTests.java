package com;

import com.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SixShooterApplicationTests {

//    @Resource(name = "jedisTemplate")
//    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
//      boolean bl = stringRedisTemplate.hasKey("1040230345@qq.com");
//      System.out.println(bl);
//      String st = stringRedisTemplate.opsForValue().get("asdasd");
//      System.out.println(st);
//        stringRedisTemplate.opsForValue().set("111","111");
        UserDto userDto =new UserDto();
        userDto.setId(111);
        userDto.setEmail("hhhhhhh@qq.com");
        redisTemplate.opsForValue().set("jackson",userDto);
        System.out.println(redisTemplate.opsForValue().get("jackson" ));
//        redisTemplate.delete("jackson");
        //redisTemplate.opsForValue().set("jackson","123");

    }

}
