package com.example.aaahome.service;

import com.example.aaahome.POJO.GameInfoSteam;
import com.example.aaahome.mapper.GISMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class StatusCheck {
    private  final GISMapper gisMapper;
    public StatusCheck(GISMapper gisMapper) {
        this.gisMapper = gisMapper;
    }
    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("alive");
    }
}
