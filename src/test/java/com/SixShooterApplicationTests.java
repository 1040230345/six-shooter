package com;

import com.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SixShooterApplicationTests {

    @Autowired
    private Jackson_Mapper_test jackson_mapper_test;

    @Test
    public void contextLoads() {
        UserDto userDto = jackson_mapper_test.findById(1);
        System.out.println(userDto.getName());
    }

}
