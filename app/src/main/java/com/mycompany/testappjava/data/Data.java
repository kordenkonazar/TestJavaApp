package com.mycompany.testappjava.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("stats")
    public Stats stats;
    @SerializedName("coins")
    public List<Coin> coins;


}
