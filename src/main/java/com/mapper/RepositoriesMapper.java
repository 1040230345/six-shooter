package com.mapper;

import com.dto.RepositoriesDto;
import com.dto.Repositories_membersDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 仓库持久层
 * 时间：2019年8月23日16:35:13
 */
@Mapper
public interface RepositoriesMapper {
    /**
     * 创建仓库
     */
    @Insert("INSERT INTO repositories_table (rep_name,rep_creator,rep_bio,isopen,githuburl,created_at) VALUES ( #{rep_name},#{rep_creator},#{rep_bio},#{isopen},#{githuburl},#{created_at} )")
    @Options(useGeneratedKeys = true, keyProperty = "rep_id",keyColumn="rep_id")
    void insertNewRep(RepositoriesDto repositoriesDto);

    /**
     * 仓库成员表
     */
    @Insert("INSERT INTO repositories_members(rep_id,user_id,rep_role,created_at) VALUES ( #{rep_id},#{user_id},#{rep_role},#{created_at})")
    int insertRepMember(Repositories_membersDto repositories_membersDto);
}
