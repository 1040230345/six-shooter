package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service("mailService")
@Transactional
public class MailService {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //发送邮箱
    public void sendSimpleMail(String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);

    }

    //获取邮箱验证码
    public boolean getCheckCode(String mail) {
        //先检查缓存中是否存在值
        boolean bl = stringRedisTemplate.hasKey(mail);
        if (bl) {
            //删除值
            stringRedisTemplate.delete(mail);
        }
        //生成验证码
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为：" + checkCode;
        //发送邮件
        sendSimpleMail(mail, "注册验证码", message);
        System.out.println(mail);
        //存入缓存
        stringRedisTemplate.opsForValue().set(mail, checkCode, 60 * 2, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
        return true;
    }
}
