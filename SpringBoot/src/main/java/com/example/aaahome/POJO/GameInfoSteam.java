package com.example.aaahome.POJO;

import java.util.Objects;

public class GameInfoSteam {
    int id;
    String gameName;
    int priceNow;
    int priceLowest;
    String coverimageUrl;
    String detailPage;
    String relaseDate;
    int nearlyLowestTimestamp;
    String mark1;
    String mark2;

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



    public GameInfoSteam(int id, String gameName, int priceNow, int priceLowest, String coverimageUrl, String detailPage, String relaseDate, int nearlyLowestTimestamp, String mark1, String mark2) {
        this.id = id;
        this.gameName = gameName;
        this.priceNow = priceNow;
        this.priceLowest = priceLowest;
        this.coverimageUrl = coverimageUrl;
        this.detailPage = detailPage;
        this.relaseDate = relaseDate;
        this.nearlyLowestTimestamp = nearlyLowestTimestamp;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    @Override
    public String toString() {
        return "GameInfoSteam{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", priceNow=" + priceNow +
                ", priceLowest=" + priceLowest +
                ", coverimageUrl='" + coverimageUrl + '\'' +
                ", detailPage='" + detailPage + '\'' +
                ", relaseDate='" + relaseDate + '\'' +
                ", nearlyLowestTimestamp=" + nearlyLowestTimestamp +
                ", mark1='" + mark1 + '\'' +
                ", mark2='" + mark2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInfoSteam that = (GameInfoSteam) o;
        return id == that.id && priceNow == that.priceNow && priceLowest == that.priceLowest && nearlyLowestTimestamp == that.nearlyLowestTimestamp && Objects.equals(gameName, that.gameName) && Objects.equals(coverimageUrl, that.coverimageUrl) && Objects.equals(detailPage, that.detailPage) && Objects.equals(relaseDate, that.relaseDate) && Objects.equals(mark1, that.mark1) && Objects.equals(mark2, that.mark2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameName, priceNow, priceLowest, coverimageUrl, detailPage, relaseDate, nearlyLowestTimestamp, mark1, mark2);
    }
}
