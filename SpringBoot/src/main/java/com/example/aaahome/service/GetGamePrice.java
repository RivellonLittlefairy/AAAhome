package com.example.aaahome.service;

import com.example.aaahome.Jedis.JedisTool;
import com.example.aaahome.POJO.Detail;
import com.example.aaahome.POJO.DetailFromDB;
import com.example.aaahome.POJO.GameInfoSteam;
import com.example.aaahome.POJO.WishItem;
import com.example.aaahome.mapper.GISMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GetGamePrice {
    private final GISMapper gisMapper;

    public GetGamePrice(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }

    @RequestMapping("getById/*")
    public void getGameById(HttpServletRequest request, HttpServletResponse response)  {
        String requestURI = request.getRequestURI();
        try {
            int i = Integer.parseInt(requestURI.substring(9));
            //设置可以读取utf8mb4
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            //response.setContentType("application/json");
            GameInfoSteam game = gisMapper.getById(i);
            WishItem item = new WishItem(game, gisMapper.getAppraise(i), gisMapper.getImgSrc(i));
            Gson gson = new Gson();
            String json = gson.toJson(item);
            response.getWriter().println(json);
        } catch (Exception e) {
            System.out.println("error request   " + requestURI);
        }
    }

    @RequestMapping("getDetailById/*")
    public void getDetailById(HttpServletRequest request, HttpServletResponse response){
        String requestURI = request.getRequestURI();
        try {
            int i = Integer.parseInt(requestURI.substring(15));
            //设置可以读取utf8mb4
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            //response.setContentType("application/json");
            //尝试从redis缓存中获取数据
            try (Jedis jedis = JedisTool.getJedis()) {
                String json = jedis.get("DetailCache" + i);
                if (json == null) {
                    //此时说明缓存中没有它的信息
                    DetailFromDB game = gisMapper.getDetailById(i);
                    Detail item = new Detail(game);
                    Gson gson = new Gson();
                    json = gson.toJson(item);
                    jedis.set("DetailCache" + i, json);
                    jedis.expire("DetailCache" + i, 60 * 60 * 12);
                    response.getWriter().println(json);
                    return;
                }
                response.getWriter().println(json);
            }
        } catch (Exception e) {
            System.out.println("error request   " + requestURI);
        }

    }
}
