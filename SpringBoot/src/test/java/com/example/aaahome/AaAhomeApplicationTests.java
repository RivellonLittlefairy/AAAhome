package com.example.aaahome;

import com.example.aaahome.mapper.GISMapper;
import com.example.aaahome.service.MailService;
import com.example.aaahome.task.CheckWishMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AaAhomeApplicationTests {
    @Autowired
    MailService service;
    @Autowired
    CheckWishMap test;

    @Test
    void contextLoads() {
        test.mainJob();
    }
}



