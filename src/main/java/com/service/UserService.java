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
    String findEmailByName(String name);
    int findUserIdByCookie(String cookie);
    UserDto findUserByid(int id);

    /**
     * 校验cookie并且修改cookie
     * @return
     */
    Boolean checkCookieAndChange(int user_id, String cookie);

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

}
