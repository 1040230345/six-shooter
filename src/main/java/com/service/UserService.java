package com.service;

/**
 * 时间：2019年8月16日16:44:24
 */
public interface UserService {

    int findByEmail(String email);
    int findByName(String name );
}
