package com.mapper;

import com.dto.CookieDto;
import com.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 时间：2019年8月16日16:43:59
 */
@Mapper
public interface UserMapper {

    /**
     * 异步校验
     * 时间：2019年8月17日13:12:03
     */
    @Select("select * from user_table where email=#{email}")
    UserDto findByEmail(@Param("email") String email);

    @Select("select * from user_table where name=#{name}")
    UserDto findByName(@Param("name") String name);

    /**
     * 注册逻辑
     * 时间：2019年8月17日13:12:47
     */
    @Insert("INSERT INTO user_table (email, name, password, created_at, updated_at ) VALUES ( #{email}, #{name}, #{password}, #{created_at}, #{updated_at} )")
    int insertUser(UserDto userDto);

    /**
     * 插入cookies
     * 时间：2019年8月17日16:35:12
     */
    @Insert("INSERT INTO cookie_table (user_id,cookie, created_at, updated_at ) VALUES ( #{user_id}, #{cookie}, #{created_at}, #{updated_at} )")
    int insertCookie(CookieDto cookieDto);

}
