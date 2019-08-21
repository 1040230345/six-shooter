package com.service;

import com.dto.CookieDto;
import com.dto.UserDto;
import org.apache.ibatis.annotations.Param;

/**
 * 时间：2019年8月16日16:44:24
 */
public interface UserService {

    UserDto findByEmail(String email);
    UserDto findByName(String name);
    int insertUser(UserDto userDto);
    int inserCookie(CookieDto cookieDto);
    UserDto findUser_login(String email_or_name,String password);
    int updateCookie(CookieDto cookieDto);
    String findEmailByName(String name);
    int findUserIdByCookie(String cookie);
    UserDto findUserByid(int id);

}
