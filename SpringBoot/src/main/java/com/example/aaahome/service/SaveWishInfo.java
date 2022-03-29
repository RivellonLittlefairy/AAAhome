package com.example.aaahome.service;

import com.example.aaahome.Jedis.JedisTool;
import com.example.aaahome.POJO.WishItem;
import com.example.aaahome.POJO.WishMap;
import com.example.aaahome.response.ResponseInfo;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class SaveWishInfo {
    String key="msfbestcoder";
    @RequestMapping("saveWishById/*")
    public void saveWishById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //通过url
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String url = request.getRequestURI();
        url=url.substring(14);
        String[] split = url.split("&");
        String email=split[0];
        int gameId=Integer.parseInt(split[1]);
        int wishPrice=Integer.parseInt(split[2]);
        response.getWriter().write(helper(email,gameId,wishPrice));
    }
    //拥有token的客户端会调用这个方法
    @RequestMapping("saveWishById")
    public void saveWishByIdWithToken(HttpServletRequest request, HttpServletResponse response){
        final Cookie[] cookies = request.getCookies();
        final String token = cookies[0].getValue();
        try{
            Jws<Claims> jws=Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            String email= (String) jws.getBody().get("email");
            final Map<Integer, Integer> map = new Gson().fromJson(request.getHeader("map"), WishMap.class).getMap();
            for(int id:map.keySet()){
                helper(email,id,map.get(id));
            }
        }catch (ExpiredJwtException e){
            response.setHeader("status","timeout");
        }


    }

    public String helper(String email,int gameId,int wishPrice){
        try{
            try(final Jedis jedis = JedisTool.getJedis()){
                jedis.auth("fs4txdya");
                if(jedis.hset("$" + email, gameId+"", wishPrice+"")==0){
                   return "失败,因为参数列表非法";
                }
            }
            return new Gson().toJson(new ResponseInfo(1,"success"));
        }catch (Exception e){
            return new Gson().toJson(new ResponseInfo(0,"存储愿望单失败！"));
        }
    }
}
