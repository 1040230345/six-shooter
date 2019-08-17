package com.service;

import com.dto.CookieDto;
import com.dto.UserDto;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findByEmail(String email) {

        UserDto userDto = userMapper.findByEmail(email);

        if(userDto!=null){
            return userDto;
        }else {
            return null;
        }

    }

    @Override
    public UserDto findByName(String name) {

        UserDto userDto  = userMapper.findByName(name);

        if(userDto!=null){
            return userDto;
        }else {
            return null;
        }
    }

    @Override
    public int insertUser(UserDto userDto) {

        int num = userMapper.insertUser(userDto);
        if(num>0){
            return 1;
        }
        return 0;
    }

    @Override
    public int inserCookie(CookieDto cookieDto) {
        int num = userMapper.insertCookie(cookieDto);
        if(num>0){
            return 1;
        }

        return 0;
    }


}
