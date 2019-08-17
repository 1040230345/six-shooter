package com.mapper;

import com.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 时间：2019年8月16日16:43:59
 */
@Mapper
public interface UserMapper {

    @Select("select * from user_table where email=#{email}")
    UserDto findByEmail(@Param("email") String email);

    @Select("select * from user_table where name=#{name}")
    UserDto findByName(@Param("name") String name);


}
