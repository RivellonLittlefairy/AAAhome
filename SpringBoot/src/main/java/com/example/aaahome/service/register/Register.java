package com.example.aaahome.service.register;

import com.example.aaahome.Jedis.JedisTool;
import com.example.aaahome.POJO.User;
import com.example.aaahome.mapper.GISMapper;
import com.example.aaahome.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Controller
public class Register {
    @Autowired
    MailService service;
    private final GISMapper gisMapper;

    public Register(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }
    @RequestMapping("register/*")
    public void register(HttpServletRequest request, HttpServletResponse response){
        final String uri = request.getRequestURI().substring(10);
        final String[] split = uri.split("&");
        String email=split[0];
        //检查是否是个邮箱
        if(!email.matches("\\w+@\\w+.com")){
            response.setHeader("status","Illegal mailbox");
            return;
        }
        //用特殊符号防止冲突
        String key="_E"+email;
        final User user = gisMapper.getUserByEmail(email);
        if(user!=null){
            response.addHeader("status","already registered");
            return;
        }
        if(split.length==1){
            //只有一个字符说明只发送了邮箱地址
            try(final Jedis jedis = JedisTool.getJedis()){
                jedis.auth("fs4txdya");
                //验证该邮箱是否短时间多次启动注册
                if(jedis.exists(key)){
                    response.setHeader("status","duplicate！");
                    return;
                }
                long verify=Math.abs(new Random().nextLong()%10000);
                jedis.set(key,verify+"");
                //设置记录两分钟后过期
                jedis.expire(key,60);
                response.setHeader("status","register begin");
                service.sendSimpleMail(email,"验证码","您的注册验证码是"+verify);
                return;
            }
        }else if(split.length==3){
            //包括了验证码，要进行验证
            try(final Jedis jedis = JedisTool.getJedis()){
                jedis.auth("fs4txdya");
                if(!jedis.exists(key)){
                    response.setHeader("status","overdue");
                    return;
                }
                final String s = jedis.get(key);
                if(s.equals(split[1])){
                    response.addHeader("status","register success");
                    gisMapper.addUser("uname",split[2],email,"uhead");
                }else{
                    response.addHeader("status","verify error");
                }
                return;
            }
        }else{
            response.addHeader("status","unknown error");
        }

    }
}
