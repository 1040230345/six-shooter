package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 时间：2019年8月16日16:43:59
 */
@Mapper
public interface UserMapper {

    @Select("select * from user_table where emain=#{email}")
    int findByEmail(@Param("email") String email);

    @Select("select * from user_table where name=#{name}")
    int findByName(@Param("name") String name);


}
