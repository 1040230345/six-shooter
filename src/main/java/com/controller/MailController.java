package com.controller;

import com.dto.CodeDto;
import com.mapper.MailMapper;
import com.my_util.GetTime_util;
import com.service.MailService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private GetTime_util getTime_util;
    @Autowired
    private UserService userService;

    @PostMapping("/vcodeSend")
    @ResponseBody
    public Map<String,String> getCheckCode(String mail){
        Map<String,String> map = new HashMap<>();
        //优先判断是用户名还是邮箱
        boolean ifmail = mail.contains("@");
        //如果不是邮箱格式
        if(!ifmail){
            //那就依靠用户名查找邮箱咯
            mail = userService.findEmailByName(mail);
            if(mail == null){
                //找不到对应的邮箱直接返回0
                map.put("email", "0");
                return map;
            }
        }
        //生成验证码
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        //先判断数据库里面是否已经有信息
        if( mailMapper.findCodeByEmail(mail)!=null) {
            //删除数据库信息
            int mun = mailMapper.delCodeByEmail(mail);
            if(mun ==0 ){
                //删除异常
                map.put("email", "0");
                return map;
            }
        }
        try {
            //发送邮件
            mailService.sendSimpleMail(mail, "注册验证码", message);
            //写入数据库
            CodeDto codeDto = new CodeDto();
            codeDto.setEmail(mail);
            codeDto.setCode(checkCode);
            codeDto.setCreated_at(getTime_util.GetNowTime_util());
            int num = mailMapper.insertCookie(codeDto);
            if(num>1){
                //写入成功
                map.put("email", "1");
                return map;
            }else {
                //失败
                map.put("email", "0");
                return map;
            }
        }catch (Exception e){ }
        map.put("email", "0");
        return map;
    }

}
