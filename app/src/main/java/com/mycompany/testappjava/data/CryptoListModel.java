package com.mycompany.testappjava.data;

import java.util.List;

public class CryptoListModel {
    private String name;
    private String shortName;
    private List<Float> spotPointList;
    private String imageUrl;
    private Float persent;
    private Float balance;

    public CryptoListModel(String name, String shortName, List<Float> spotPointList, String imageUrl, Float persent, Float balance) {
        this.name = name;
        this.shortName = shortName;
        this.spotPointList = spotPointList;
        this.imageUrl = imageUrl;
        this.persent = persent;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Float> getSpotPointList() {
        return spotPointList;
    }

    public void setSpotPointList(List<Float> spotPointList) {
        this.spotPointList = spotPointList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getPersent() {
        return persent;
    }

    public void setPersent(Float persent) {
        this.persent = persent;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }


}
