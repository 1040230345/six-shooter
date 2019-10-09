package com.service;

import com.dto.CookieDto;
import com.dto.UserDto;
import com.mapper.MailMapper;
import com.mapper.UserMapper;
import com.my_util.Encryption;
import com.my_util.GetTime_util;
import com.my_util.PlayFair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GetTime_util getTime_util;
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource(name = "userRedisTemplate")
    private RedisTemplate redisTemplate;
    @Autowired
    private PlayFair playFair;
    @Autowired
    private Encryption encryption;


    /**
     * 验证验证码是否正确
     * @return
     */
    @Override
    public Boolean checkCode(String emailOrName,String Vcode) {
        //判断传进来的是用户名还是邮箱
        boolean ifmail = emailOrName.contains("@");
        if (!ifmail) {
            //缓存中获取邮箱
            String email = stringRedisTemplate.opsForValue().get(emailOrName);
            if(email==null){
                //往数据库中获取邮箱
                email = userMapper.findEmailByName(emailOrName);
                if(email!=null){
                    //将获取到的数据存在缓存中
                    stringRedisTemplate.opsForValue().set(emailOrName, email, 60 * 2, TimeUnit.SECONDS);
                    //验证码是否存在
                    if(stringRedisTemplate.hasKey(email)){
                        //验证验证码是否正确
                        if(Vcode.equals(stringRedisTemplate.opsForValue().get(email))){
                            return true;
                        }
                    }
                }
                return false;
            }
            //验证验证码是否正确
            if(Vcode.equals(stringRedisTemplate.opsForValue().get(email))){
                return true;
            }
            return false;
        }
        //验证验证码是否正确
        if(Vcode.equals(stringRedisTemplate.opsForValue().get(emailOrName))){
            return true;
        }
        return false;
    }

    /**
     * 登录的账号密码验证
     * @param email_or_name
     * @param password
     * @return
     */
    @Override
    public UserDto checkLogin(String email_or_name, String password) {
        //System.out.println(password);
        UserDto userDto = userMapper.findUser_login(email_or_name,password);
        if(userDto!=null){
            return userDto;
        }
        return null;
    }

    /**
     * 创建并返回cookie
     * 修改时间：2019年10月5日17:03:03
     * @param userDto
     * @return
     */
    @Override
    public String updateCookie(UserDto userDto) {
//        //创建cookie
//        CookieDto cookieDto = new CookieDto();
//        //获取UUID
//        String token = UUID.randomUUID().toString();
//        cookieDto.setCookie(token);
//        cookieDto.setUser_id(userDto.getId());
//        cookieDto.setUpdated_at(getTime_util.GetNowTime_util());
//        //更新cookie
//        int num = userMapper.updateCookie(cookieDto);
//        if(num>0){
//            return token;
//        }
        //创建令牌
        String token = playFair.begin_Play();
        //设置默认头像
        if (userDto.getAvatar_url() == null) {
            userDto.setAvatar_url("/static/images/default.jpg");
        }
        //将用户信息存放在缓存中,存在时间30分钟
        redisTemplate.opsForValue().set(token,userDto,60 * 60 * 30, TimeUnit.SECONDS);
        return token;
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
       // System.out.println(userDto.toString());
        if(num>0){
            //获取用户信息
            userDto = userMapper.findByName(userDto.getName());
            if(userDto!=null){
                //boolean bl = mkdirCookie(userDto.getId());
                return userDto;
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

    /**
     * 解密
     * 时间：2019年10月8日17:24:43
     * @param ciphertext
     * @return
     */
    @Override
    public String decrypt(String ciphertext) {
        ciphertext = encryption.Out(ciphertext);
        return ciphertext;
    }


}
