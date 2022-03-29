package com.example.aaahome.POJO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Detail {
    private int id;
    private String gameName;
    //steam原页面
    private String url;
    //当前价格
    private int price;
    //原价
    private int highPrice;
    //是不是dlc，1表示不是dlc
    private boolean isDlc;
    private String appraise;
    //简介
    private String comments;
    //dlc名称列表
    private List<String> dlcNames;
    //封面图
    private String HDCoverImg;
    //标签
    private List<String> tag;
    //多个宣传图片，1920*1080
    private List<String> HDImg;
    //多个宣传视频，1920*1080
    private List<String> HDMovie;
    private String mark1;
    private String mark2;


    public Detail(DetailFromDB db){
        this.id=db.getId();
        this.gameName=db.getGameName();
        this.url=db.getUrl();
        this.price=db.getPrice();
        this.highPrice=db.getHighPrice();
        this.isDlc=db.isDlc();
        this.appraise=db.getAppraise();
        this.comments =db.getComments();
        this.HDCoverImg=db.getHDCoverImg();
        this.mark1=db.getMark1();
        this.mark2=db.getMark2();
        if(db.getDlcNames().length()!=0) this.dlcNames= Arrays.asList(db.getDlcNames().substring(1).split(","));
        if(db.getTag().length()!=0)this.tag= Arrays.asList(db.getTag().substring(1).split(","));
        if(db.getHDImg().length()!=0)this.HDImg= Arrays.asList(db.getHDImg().substring(1).split(","));
        if(db.getHDMovie().length()!=0)this.HDMovie= Arrays.asList(db.getHDMovie().substring(1).split(","));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detail detail = (Detail) o;
        return id == detail.id && price == detail.price && highPrice == detail.highPrice && isDlc == detail.isDlc && Objects.equals(gameName, detail.gameName) && Objects.equals(url, detail.url) && Objects.equals(appraise, detail.appraise) && Objects.equals(comments, detail.comments) && Objects.equals(dlcNames, detail.dlcNames) && Objects.equals(HDCoverImg, detail.HDCoverImg) && Objects.equals(tag, detail.tag) && Objects.equals(HDImg, detail.HDImg) && Objects.equals(HDMovie, detail.HDMovie) && Objects.equals(mark1, detail.mark1) && Objects.equals(mark2, detail.mark2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameName, url, price, highPrice, isDlc, appraise, comments, dlcNames, HDCoverImg, tag, HDImg, HDMovie, mark1, mark2);
    }

    public Detail(int id, String gameName, String url, int price, int highPrice, boolean isDlc, String appraise, String comment, List<String> dlcNames, String HDCoverImg, List<String> tag, List<String> HDImg, List<String> HDMovie, String mark1, String mark2) {
        this.id = id;
        this.gameName = gameName;
        this.url = url;
        this.price = price;
        this.highPrice = highPrice;
        this.isDlc = isDlc;
        this.appraise = appraise;
        this.comments = comment;
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

    public List<String> getDlcNames() {
        return dlcNames;
    }

    public void setDlcNames(List<String> dlcNames) {
        this.dlcNames = dlcNames;
    }

    public String getHDCoverImg() {
        return HDCoverImg;
    }

    public void setHDCoverImg(String HDCoverImg) {
        this.HDCoverImg = HDCoverImg;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> getHDImg() {
        return HDImg;
    }

    public void setHDImg(List<String> HDImg) {
        this.HDImg = HDImg;
    }

    public List<String> getHDMovie() {
        return HDMovie;
    }

    public void setHDMovie(List<String> HDMovie) {
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