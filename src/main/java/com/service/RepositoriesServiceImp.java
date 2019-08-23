package com.service;

import com.dto.RepositoriesDto;
import com.dto.Repositories_membersDto;
import com.dto.UserDto;
import com.mapper.RepositoriesMapper;
import com.mapper.UserMapper;
import com.my_util.GetTime_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RepositoriesServiceImp implements RepositoriesService {
    @Autowired
    private GetTime_util getTime_util;
    @Autowired
    private RepositoriesMapper repositoriesMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 实现用户仓库创建
     * @param repositoriesDto
     * @return
     */
    @Override
    public boolean mkdirRepositories(RepositoriesDto repositoriesDto) {
        //获取现在时间
        repositoriesDto.setCreated_at(getTime_util.GetNowTime_util());
        //数据库仓库创建
        repositoriesMapper.insertNewRep(repositoriesDto);

        //在成员表中插入数据
        Repositories_membersDto repositories_membersDto = new Repositories_membersDto();
        //获取项目id
        repositories_membersDto.setRep_id(repositoriesDto.getRep_id());
        //依靠用户名获取用户id
        UserDto userDto = userMapper.findByName(repositoriesDto.getRep_creator());
        repositories_membersDto.setUser_id(userDto.getId());
        //设置角色
        repositories_membersDto.setRep_role("创建者");
        //设置时间
        repositories_membersDto.setCreated_at(getTime_util.GetNowTime_util());
        //插入成员表
        int num = repositoriesMapper.insertRepMember(repositories_membersDto);
        if(num>0){
            return true;
        }
        return false;

    }
}
