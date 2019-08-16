package com;

import com.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Jackson_Mapper_test {

    @Select("SELECT * FROM user_table WHERE id = #{id}")
    UserDto findById(@Param("id") int id);

}
