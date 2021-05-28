package com.mycompany.testappjava.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Coin{
    public String uuid;
    public String symbol;
    public String name;

    public Coin(String uuid, String symbol, String name, String color, String iconUrl, String marketCap, String price, String btcPrice, int listedAt, String change, int rank, List<String> sparkline, String coinrankingUrl, String volume24) {
        this.uuid = uuid;
        this.symbol = symbol;
        this.name = name;
        this.color = color;
        this.iconUrl = iconUrl;
        this.marketCap = marketCap;
        this.price = price;
        this.btcPrice = btcPrice;
        this.listedAt = listedAt;
        this.change = change;
        this.rank = rank;
        this.sparkline = sparkline;
        this.coinrankingUrl = coinrankingUrl;
        this.volume24 = volume24;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBtcPrice() {
        return btcPrice;
    }

    public void setBtcPrice(String btcPrice) {
        this.btcPrice = btcPrice;
    }

    public int getListedAt() {
        return listedAt;
    }

    public void setListedAt(int listedAt) {
        this.listedAt = listedAt;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<String> getSparkline() {
        return sparkline;
    }

    public void setSparkline(List<String> sparkline) {
        this.sparkline = sparkline;
    }

    public String getCoinrankingUrl() {
        return coinrankingUrl;
    }

    public void setCoinrankingUrl(String coinrankingUrl) {
        this.coinrankingUrl = coinrankingUrl;
    }

    public String getVolume24() {
        return volume24;
    }

    public void setVolume24(String volume24) {
        this.volume24 = volume24;
    }

    public String color;
    public String iconUrl;
    public String marketCap;
    public String price;
    public String btcPrice;
    public int listedAt;
    public String change;
    public int rank;
    public List<String> sparkline;
    public String coinrankingUrl;
    @SerializedName("24hVolume")
    public String volume24;
}