package com.example.aaahome.task;

import com.example.aaahome.Jedis.JedisTool;
import com.example.aaahome.POJO.GameInfoSteam;
import com.example.aaahome.mapper.GISMapper;
import com.example.aaahome.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.Jedis;

import java.util.Date;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class SYNTable {
    @Autowired
    MailService service;
    private  final GISMapper gisMapper;
    public SYNTable(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void SYNPrice(){
        final int gameNumber = gisMapper.getGameNumber();
        for (int i = 1; i <= gameNumber; i++) {
            final GameInfoSteam game = gisMapper.getById(i);
            gisMapper.saveDetailPrice(Integer.parseInt(game.getMark1()),game.getPriceNow(),i);
        }
    }
    @Scheduled(cron = "0 0 11 * * ?")
    public void SYNName(){
        int begin=0;
        try(final Jedis jedis = JedisTool.getJedis()){
            final String s = jedis.get("name_change");
            begin=Integer.parseInt(s);
        }
        for (int i = begin; i < gisMapper.getGameNumber(); i++) {
            gisMapper.changeName(gisMapper.getDetailById(i).getGameName(),i);
            try(final Jedis jedis = JedisTool.getJedis()){
                jedis.set("name_change",i+"");
            }
        }
    }

}
