package com.mycompany.testappjava.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinsResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("data")
    public Data data;

}
