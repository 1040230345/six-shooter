package com.service;

import com.dto.CookieDto;
import com.dto.UserDto;
import com.mapper.MailMapper;
import com.mapper.UserMapper;
import com.my_util.GetTime_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GetTime_util getTime_util;
    @Autowired
    private MailMapper mailMapper;

    @Override
    public UserDto findByEmail(String email) {

        UserDto userDto = userMapper.findByEmail(email);

        if(userDto!=null){
            return userDto;
        }else {
            return null;
        }

    }

    @Override
    public UserDto findByName(String name) {

        UserDto userDto  = userMapper.findByName(name);

        if(userDto!=null){
            return userDto;
        }else {
            return null;
        }
    }

    @Override
    public String findEmailByName(String name) {
        String email = userMapper.findEmailByName(name);
        return email;
    }

    //依靠cookie查找用户id
    @Override
    public int findUserIdByCookie(String cookie) {
        int user_id = userMapper.findUserIdByCookie(cookie);
        return user_id;
    }

    @Override
    public UserDto findUserByid(int id) {
        UserDto userDto = userMapper.findUserByid(id);
        return userDto;
    }

    /**
     * 校验cookie并且修改
     * @param cookie
     * @return
     */
    @Override
    public Boolean checkCookieAndChange(int user_id ,String cookie) {
        //对比cookie是否一致
        String find_cookie = userMapper.findCookieById(user_id);
        if(find_cookie.equals(cookie)){

        }

        return null;
    }

    /**
     * 验证验证码是否正确
     * @return
     */
    @Override
    public Boolean checkCode(String email,String Vcode) {

        //判断传进来的是用户名还是邮箱
        boolean ifmail = email.contains("@");
        if(!ifmail){
            //获取邮箱
            String mail = userMapper.findEmailByName(email);
            //进行比对
            String code = mailMapper.findCodeByEmail(mail);
            //不正确
            if(!Vcode.equals(code)){
               return false;
            }else{
                return true;
            }
        }else{//是邮箱，直接对比
            String code = mailMapper.findCodeByEmail(email);
            //不正确
            if(!Vcode.equals(code)){
                return false;
            }else {
                return true;
            }
        }
    }

    /**
     * 登录的账号密码验证
     * @param email_or_name
     * @param password
     * @return
     */
    @Override
    public UserDto checkLogin(String email_or_name, String password) {
        UserDto userDto = userMapper.findUser_login(email_or_name,password);
        if(userDto!=null){
            return userDto;
        }
        return null;
    }

    /**
     * 登录更新cookie表
     * @param userDto
     * @return
     */
    @Override
    public String updateCookie(UserDto userDto) {
        //创建cookie
        CookieDto cookieDto = new CookieDto();
        //获取UUID
        String token = UUID.randomUUID().toString();
        cookieDto.setCookie(token);
        cookieDto.setUser_id(userDto.getId());
        //更新cookie
        int num = userMapper.updateCookie(cookieDto);
        if(num>0){
            return token;
        }
        return null;
    }

    /**
     * 删除验证码记录
     * @param email
     * @return
     */
    @Override
    public boolean delCode(String email) {
        //判断传进来的是用户名还是邮箱
        boolean ifmail = email.contains("@");
        if(!ifmail){
            //System.out.println("我进来了");
            email = userMapper.findEmailByName(email);
            mailMapper.delCodeByEmail(email);
        }else {
            mailMapper.delCodeByEmail(email);
        }
        return true;
    }

    /**
     * 注册事务
     * @param userDto
     * @return
     */
    @Override
    public UserDto register(UserDto userDto) {
        // 赋值创建时间和修改时间
        userDto.setCreated_at(getTime_util.GetNowTime_util());
        userDto.setUpdated_at(getTime_util.GetNowTime_util());
        //插入数据库
        int num = userMapper.insertUser(userDto);
        if(num>0){
            //获取用户信息
            userDto = userMapper.findByName(userDto.getName());
            if(userDto!=null){
                boolean bl = mkdirCookie(userDto.getId());
                if(bl){
                    return userDto;
                }
                return null;
            }
        }
        return null;
    }

    /**
     * 创建cookie
     * @param id
     * @return
     */
    @Override
    public Boolean mkdirCookie(int id) {
        //获取UUID
        String token = UUID.randomUUID().toString();
        //插入cookies数据库
        CookieDto cookieDto = new CookieDto();
        cookieDto.setCookie(token);
        cookieDto.setUser_id(id);
        cookieDto.setCreated_at(getTime_util.GetNowTime_util());
        cookieDto.setUpdated_at(getTime_util.GetNowTime_util());
        int inser = userMapper.insertCookie(cookieDto);
        if(inser>0){
            return true;
        }
        return false;
    }


}
