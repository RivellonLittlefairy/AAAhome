package com.example.aaahome.service.login;

import com.example.aaahome.POJO.User;
import com.example.aaahome.mapper.GISMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class Login {
    private final GISMapper gisMapper;

    public Login(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }

    //设置三十一天的过期时间
    long overdue=15*24*60*60*1000;
    String pkey="msfbestcoder";

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        final String json = request.getHeader("requestLogin");
        Gson gson=new Gson();
        final RequestLogin requestLogin = gson.fromJson(json, RequestLogin.class);
        if(requestLogin==null){
            System.out.println("null");
            return;
        }
        //检查用户名密码是否正确
        final User user = gisMapper.getUserByEmail(requestLogin.email);
        if(user==null||!user.getPwd().equals(requestLogin.pwd)){
            //-1表示用户不存在或者密码错误
            response.setHeader("status","-1");
            return;
        }
        //返回token
        JwtBuilder builder= Jwts.builder();
        String token=builder
                //设置头部信息
                .setHeaderParam("type","jwt")
                .setHeaderParam("alg","HS256")
                //设置负载信息
                .claim("email",requestLogin.email)
                .claim("pwd",requestLogin.email)
                .claim("pid",requestLogin.pid)
                .claim("uid",user.getUid())
                .setExpiration(new Date(System.currentTimeMillis()+overdue))
                .signWith(SignatureAlgorithm.HS256,pkey)
                .compact();
        System.out.println(new Date(System.currentTimeMillis()+overdue));
        response.setHeader("status","1");
        response.addCookie(new Cookie("token",token));
    }
}
