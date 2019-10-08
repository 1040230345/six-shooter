package com.service;

import com.dto.UserDto;

/**
 * 时间：2019年8月16日16:44:24
 */
public interface UserService {

    /**
     * 验证验证码是否正确
     */
    Boolean checkCode(String email,String Vcode);

    /**
     * 登录验证
     */
    UserDto checkLogin(String email_or_name, String password);

    /**
     * 更新cookie
     */
    String updateCookie(UserDto userDto);

    /**
     * 删除验证码记录
     */
    boolean delCode(String email);

    /**
     * 注册事务
     */
    UserDto register(UserDto userDto);

    /**
     * 创建cookie
     */
    Boolean mkdirCookie(int id );

    /**
     * 密文解密
     * 时间：2019年10月8日17:15:15
     */
    String decrypt(String ciphertext);

}
