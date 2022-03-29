package com.example.aaahome.task;

import com.example.aaahome.Jedis.JedisTool;
import com.example.aaahome.POJO.GameInfoSteam;
import com.example.aaahome.mapper.GISMapper;
import com.example.aaahome.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class CheckWishMap {
    @Autowired
    MailService service;
    private  final GISMapper gisMapper;

    public CheckWishMap(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }
    @Scheduled(cron = "0 0 9 * * ?")
    public void mainJob(){
        System.out.println("job begin"+new Date(System.currentTimeMillis()));
        final int userNumber = gisMapper.getUserNumber();
        for (int i = 1; i <=userNumber ; i++) {
            checkWishMap(gisMapper.getUserEmail(i));
        }
    }
    public void checkWishMap(String email){
        try(Jedis jedis=JedisTool.getJedis()){
            Map<String, String> map = jedis.hgetAll("$" + email);
            for(String gameId:map.keySet()){
                comparePrice(email,Integer.parseInt(gameId),Integer.parseInt(map.get(gameId)));
            }
        }
    }
    private void comparePrice(String email,int gameId,int wishPrice){
        final int price = gisMapper.getPriceById(gameId);
        //价格高于期望，退出
        if(price>wishPrice) return;
        sendEmail(email,gameId,price,wishPrice);

    }
    private void sendEmail(String email,int gameId,int price,int wishPrice){
        try(Jedis jedis=JedisTool.getJedis()){
            jedis.hdel("$"+email,gameId+"");
        }
        final GameInfoSteam game = gisMapper.getById(gameId);
        service.sendSimpleMail(email,"您关注的"+game.getGameName()+"已经降价到"+price/100,"请访问\n"+game.getDetailPage());
        System.out.println(email+"--"+gameId+"--"+price+"--"+wishPrice);
    }
}
