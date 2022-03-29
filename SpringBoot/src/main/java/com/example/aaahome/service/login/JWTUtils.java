package com.example.aaahome.service.login;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component
public class JWTUtils {

    public static void main(String[] args) {
        JwtBuilder builder= Jwts.builder();
        String token=builder
                .setHeaderParam("type","jwt")
                .setHeaderParam("alg","HS256")
                .claim("email","1017629235@qq.com")
                .setExpiration(new Date(System.currentTimeMillis()+100000000))
                .signWith(SignatureAlgorithm.HS256,"MSFMSFMSF")
                .setSubject("main")
                .setId(UUID.randomUUID().toString())
                .compact();
        System.out.println(token);
    }



}
