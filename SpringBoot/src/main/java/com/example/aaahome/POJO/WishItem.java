package com.example.aaahome.POJO;

public class WishItem {
    int id;
    String gameName;
    //当前价格
    int priceNow;
    //史低价格
    int priceLowest;
    //封面图地址，不一定有
    String coverimageUrl;
    //steam源页
    String detailPage;
    //发布日期
    String relaseDate;
    //最近史低价格时间戳
    int nearlyLowestTimestamp;
    //评价信息
    String appraise;
    //分辨率不高的封面图，一定有
    String imgSrc;
    //原价
    String mark1;
    String mark2;

    public WishItem(GameInfoSteam info,String appraise, String imgSrc) {
        this.appraise = appraise;
        this.imgSrc = imgSrc;
        this.id=info.id;
        this.gameName=info.gameName;
        this.priceNow=info.priceNow;
        this.priceLowest=info.priceLowest;
        this.mark1=info.mark1;
        this.mark2=info.mark2;
        this.coverimageUrl=info.coverimageUrl;
        this.detailPage=info.detailPage;
        this.relaseDate=info.relaseDate;
        this.nearlyLowestTimestamp=info.nearlyLowestTimestamp;
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

    public int getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(int priceNow) {
        this.priceNow = priceNow;
    }

    public int getPriceLowest() {
        return priceLowest;
    }

    public void setPriceLowest(int priceLowest) {
        this.priceLowest = priceLowest;
    }

    public String getCoverimageUrl() {
        return coverimageUrl;
    }

    public void setCoverimageUrl(String coverimageUrl) {
        this.coverimageUrl = coverimageUrl;
    }

    public String getDetailPage() {
        return detailPage;
    }

    public void setDetailPage(String detailPage) {
        this.detailPage = detailPage;
    }

    public String getRelaseDate() {
        return relaseDate;
    }

    public void setRelaseDate(String relaseDate) {
        this.relaseDate = relaseDate;
    }

    public int getNearlyLowestTimestamp() {
        return nearlyLowestTimestamp;
    }

    public void setNearlyLowestTimestamp(int nearlyLowestTimestamp) {
        this.nearlyLowestTimestamp = nearlyLowestTimestamp;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
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
