package com.example.aaahome.Jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTool {
    private JedisTool(){}
    private static JedisPool jedisPool;
    static {
        jedisPool=new JedisPool("124.223.63.146",6379);
    }
    public static Jedis getJedis(){
        final Jedis jedis = jedisPool.getResource();
        jedis.auth("fs4txdya");
        System.out.println("exam");
        return jedis;
    }
}
