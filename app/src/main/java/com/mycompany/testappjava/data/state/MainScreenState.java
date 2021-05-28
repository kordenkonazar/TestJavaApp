package com.mycompany.testappjava.data.state;

public class MainScreenState {
    public Boolean isLoading = false;
    public Boolean isError = false;
    public String errorDescription = "";
    public Integer currentValues = 1;
    public Boolean isSortAsk = false;

    public MainScreenState(Integer currentValues, Boolean isSortAsk) {
        this.currentValues = currentValues;
        this.isSortAsk = isSortAsk;
    }

    public MainScreenState(Boolean isLoading) {
        this.isLoading = isLoading;
    }

    public MainScreenState(Boolean isError, String errorDescription) {
        this.isError = isError;
        this.errorDescription = errorDescription;
    }
}
