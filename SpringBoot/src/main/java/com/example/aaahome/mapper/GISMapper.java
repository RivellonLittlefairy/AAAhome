package com.example.aaahome.mapper;

import com.example.aaahome.POJO.DetailFromDB;
import com.example.aaahome.POJO.GameInfoSteam;
import com.example.aaahome.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GISMapper {
    GameInfoSteam getById(int id);

    String getAppraise(int id);

    String getImgSrc(int id);

    DetailFromDB getDetailById(int id);

    int getPriceById(int id);

    User getUserByEmail(String email);

    void addUser(String uname,String pwd,String email,String uhead);

    int getUserNumber();

    int getGameNumber();

    String getUserEmail(int id);

    void saveDetailPrice(int price,int priceNow,int id);

    void changeName(String name,int id);

    List<GameInfoSteam> getByNamePrefix(String name);

    Integer[] getLikeName(String name);
}
