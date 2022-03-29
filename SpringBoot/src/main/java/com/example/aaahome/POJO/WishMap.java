package com.example.aaahome.POJO;

import java.util.HashMap;
import java.util.Map;

public class WishMap {
    Map<Integer,Integer> map;

    public WishMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }
}
