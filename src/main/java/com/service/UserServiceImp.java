package com.service;

import com.dto.UserDto;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int findByEmail(String email) {

        UserDto userDto = userMapper.findByEmail(email);

        if(userDto!=null){
            return 1;
        }else {
            return 0;
        }

    }

    @Override
    public int findByName(String name) {

        UserDto userDto  = userMapper.findByName(name);

        if(userDto!=null){
            return 1;
        }else {
            return 0;
        }
    }


}
