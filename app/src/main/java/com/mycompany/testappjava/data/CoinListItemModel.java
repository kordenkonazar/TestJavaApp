package com.mycompany.testappjava.data;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class CoinListItemModel{

    public String symbol;
    public String name;
    public Float value;
    public String iconUrl;
    public String change;
    public List<String> sparkline;

    public CoinListItemModel(String symbol, String name, String iconUrl, Float value, String change, List<String> sparkline) {
        this.symbol = symbol;
        this.name = name;
        this.iconUrl = iconUrl;
        this.change = change;
        this.sparkline = sparkline;
        this.value = value;
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


    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public List<String> getSparkline() {
        return sparkline;
    }

    public void setSparkline(List<String> sparkline) {
        this.sparkline = sparkline;
    }

    public String getPriceFormat(){
        float number = value;
        if(number < 100000){
            return String.format("%.2f", number);
        }
        String[] denominations = {"", "K", "M", "B", "T"};
        int denominationIndex = 0;

        // If number is greater than 1000, divide the number by 1000 and
        // increment the index for the denomination.
        while(number > 1000.0)
        {
            denominationIndex++;
            number = number / 1000.0F;
        }

        // To round it to 2 digits.
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);


        // Add the number with the denomination to get the final value.
        return bigDecimal + denominations[denominationIndex];

    }

}