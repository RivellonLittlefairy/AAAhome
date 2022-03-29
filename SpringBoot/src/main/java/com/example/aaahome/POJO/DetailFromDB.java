package com.example.aaahome.POJO;

public class DetailFromDB {
    private int id;
    private String gameName;
    private String url;
    private int price;
    private int highPrice;
    private boolean isDlc;
    private String appraise;
    private String comments;
    private String dlcNames;
    private String HDCoverImg;
    private String tag;
    private String HDImg;
    private String HDMovie;
    private String mark1;
    private String mark2;

    public DetailFromDB() {
    }

    public DetailFromDB(int id, String gameName, String url, int price, int highPrice, boolean isDlc, String appraise, String comments, String dlcNames, String HDCoverImg, String tag, String HDImg, String HDMovie, String mark1, String mark2) {
        this.id = id;
        this.gameName = gameName;
        this.url = url;
        this.price = price;
        this.highPrice = highPrice;
        this.isDlc = isDlc;
        this.appraise = appraise;
        this.comments = comments;
        this.dlcNames = dlcNames;
        this.HDCoverImg = HDCoverImg;
        this.tag = tag;
        this.HDImg = HDImg;
        this.HDMovie = HDMovie;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }

    public boolean isDlc() {
        return isDlc;
    }

    public void setDlc(boolean dlc) {
        isDlc = dlc;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDlcNames() {
        return dlcNames;
    }

    public void setDlcNames(String dlcNames) {
        this.dlcNames = dlcNames;
    }

    public String getHDCoverImg() {
        return HDCoverImg;
    }

    public void setHDCoverImg(String HDCoverImg) {
        this.HDCoverImg = HDCoverImg;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHDImg() {
        return HDImg;
    }

    public void setHDImg(String HDImg) {
        this.HDImg = HDImg;
    }

    public String getHDMovie() {
        return HDMovie;
    }

    public void setHDMovie(String HDMovie) {
        this.HDMovie = HDMovie;
    }

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }
}
