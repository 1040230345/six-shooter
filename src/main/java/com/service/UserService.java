package com.service;

import com.dto.CookieDto;
import com.dto.UserDto;

/**
 * 时间：2019年8月16日16:44:24
 */
public interface UserService {

    UserDto findByEmail(String email);
    UserDto findByName(String name);
    int insertUser(UserDto userDto);
    int inserCookie(CookieDto cookieDto);
}
