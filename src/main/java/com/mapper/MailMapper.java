package com.mapper;
import com.dto.CodeDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MailMapper {
    /**
     * 将验证码和邮件地址存入数据库
     */
    @Insert("INSERT INTO code_table (email, code, created_at) VALUES ( #{email}, #{code}, #{created_at} )")
    int insertCookie(CodeDto codeDto);

    /**
     * 依靠邮箱查找是否有信息
     */
    @Select("Select code from code_table where email = #{email}")
    String findCodeByEmail(@Param("email") String email);

    /**
     * 删除数据库验证码信息
     */
    @Delete("DELETE FROM code_table WHERE email = #{email};")
    int delCodeByEmail(@Param("email") String email);
}
