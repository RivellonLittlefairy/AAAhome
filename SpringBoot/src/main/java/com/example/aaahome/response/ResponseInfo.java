package com.example.aaahome.response;

public class ResponseInfo {
    private int mark;
    private String info;

    public ResponseInfo(int mark, String info) {
        this.mark = mark;
        this.info = info;
    }

    public ResponseInfo() {
        mark = 1;
        info="success";
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
