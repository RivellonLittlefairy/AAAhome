package com.example.aaahome.service;

import com.example.aaahome.Jedis.JedisTool;
import com.example.aaahome.POJO.WishMap;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class ChangeWishInfo {
    String key="msfbestcoder";
    @RequestMapping("changeWishById")
    public void changeWishById(HttpServletRequest request, HttpServletResponse response){
        final Cookie[] cookies = request.getCookies();
        final String token = cookies[0].getValue();
        try{
            Jws<Claims> jws= Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            String email= (String) jws.getBody().get("email");
            final String gid = request.getHeader("gid");
            String wishPrice=request.getHeader("wishPrice");
            System.out.println(email);
            System.out.println(gid);
            System.out.println(wishPrice);
            try(Jedis jedis= JedisTool.getJedis()) {
                jedis.hset("$"+email,gid,wishPrice);
            }
        }catch (ExpiredJwtException e){
            response.setHeader("status","timeout");
        }
    }
    @RequestMapping("removeWishById")
    public void removeWishById(HttpServletRequest request, HttpServletResponse response){
        final Cookie[] cookies = request.getCookies();
        final String token = cookies[0].getValue();
        try{
            Jws<Claims> jws= Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            String email= (String) jws.getBody().get("email");
            final String gid = request.getHeader("gid");
            try(Jedis jedis= JedisTool.getJedis()) {
                jedis.hdel("$"+email,gid);
            }
        }catch (ExpiredJwtException e){
            response.setHeader("status","timeout");
        }
    }
}
