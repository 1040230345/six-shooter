package com.service;

import com.dto.UserDto;

/**
 * 时间：2019年8月16日16:44:24
 */
public interface UserService {

    int findByEmail(String email);
    int findByName(String name );
    int insertUser(UserDto userDto);
}
