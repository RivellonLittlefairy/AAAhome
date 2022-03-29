package com.example.aaahome.service;

import com.example.aaahome.POJO.Detail;
import com.example.aaahome.POJO.DetailFromDB;
import com.example.aaahome.POJO.GameInfoSteam;
import com.example.aaahome.POJO.WishItem;
import com.example.aaahome.mapper.GISMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GetGamePrice{
    private final GISMapper gisMapper;

    public GetGamePrice(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }

    @RequestMapping("getById/*")
    public void getGameById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();
        try{
            int i = Integer.parseInt(requestURI.substring(9));
            //设置可以读取utf8mb4
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            //response.setContentType("application/json");
            GameInfoSteam game = gisMapper.getById(i);
            WishItem item=new WishItem(game,gisMapper.getAppraise(i),gisMapper.getImgSrc(i));
            Gson gson=new Gson();
            String json=gson.toJson(item);
            response.getWriter().println(json);
        }catch (Exception e){
            System.out.println("error request   "+requestURI);
        }
    }

    @RequestMapping("getDetailById/*")
    public void getDetailById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();
        try{
            int i = Integer.parseInt(requestURI.substring(15));
            //设置可以读取utf8mb4
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            //response.setContentType("application/json");
            DetailFromDB game=gisMapper.getDetailById(i);
            Detail item=new Detail(game);
            Gson gson=new Gson();
            String json=gson.toJson(item);
            response.getWriter().println(json);
        }catch (Exception e){
            System.out.println("error request   "+requestURI);
        }

    }
}
