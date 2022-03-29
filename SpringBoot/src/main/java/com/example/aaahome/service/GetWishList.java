package com.example.aaahome.service;

import com.example.aaahome.Jedis.JedisTool;
import com.example.aaahome.POJO.WishMap;
import com.example.aaahome.mapper.GISMapper;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GetWishList {
    String key="msfbestcoder";
    private final GISMapper gisMapper;

    public GetWishList(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }

    @RequestMapping("getWishList")
    public void getWishList(HttpServletRequest request, HttpServletResponse response){
        final Cookie[] cookies = request.getCookies();
        final String token = cookies[0].getValue();
        try{
            Jws<Claims> jws= Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            String email= (String) jws.getBody().get("email");
            Map<Integer,Integer> map=new HashMap<>();
            try(Jedis jedis= JedisTool.getJedis()) {
                jedis.auth("fs4txdya");
                final Map<String, String> StringMap = jedis.hgetAll("$" + email);
                for(String i:StringMap.keySet()){
                    map.put(Integer.parseInt(i),Integer.parseInt(StringMap.get(i)));
                }
                final String json = new Gson().toJson(new WishMap(map));
                response.getWriter().write(json);
            }
        }catch (ExpiredJwtException e){
            response.setHeader("status","timeout");
        }catch (IOException e){

        }
    }
}
