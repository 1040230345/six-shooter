package com.service;

import com.dto.CookieDto;
import com.dto.UserDto;
import com.mapper.UserMapper;
import com.my_util.GetTime_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GetTime_util getTime_util;

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

    @Override
    public UserDto findUser_login(String email_or_name,String password) {

        UserDto userDto = userMapper.findUser_login(email_or_name,password);

        if(userDto!=null){
            return userDto;
        }

        return null;
    }

    /**
     * 更新cookie
     * @param cookieDto
     * @return
     */
    @Override
    public int updateCookie(CookieDto cookieDto) {
        cookieDto.setUpdated_at(getTime_util.GetNowTime_util());

        int num = userMapper.updateCookie(cookieDto);

        if(num>0){
            return 1;
        }
        return 0;
    }


}
