package com.mycompany.testappjava.repositories;


import static com.mycompany.testappjava.Constants.COINRANKING_BASE_URL;
import static com.mycompany.testappjava.Constants.COINRANKING_TOKEN;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.testappjava.api.CoinrankingApi;
import com.mycompany.testappjava.data.CoinsResponse;
import com.mycompany.testappjava.data.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

public class CoinrankingRepository {

    private static CoinrankingRepository instance;

    public static CoinrankingRepository getInstance() {
        if (instance == null) {
            instance = new CoinrankingRepository();
        }
        return instance;
    }

    private CoinrankingApi coinrankingApi;

    private CoinrankingRepository() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(COINRANKING_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        coinrankingApi = retrofit.create(CoinrankingApi.class);
    }

    public LiveData<Resource<CoinsResponse>> getCoins(String orderBy, String orderDirection, Integer limit, Integer offset){
        final MutableLiveData<Resource<CoinsResponse>> data = new MutableLiveData<>();
        data.setValue(new Resource.Loading());
        coinrankingApi.getCoins(COINRANKING_TOKEN, orderBy, orderDirection, limit, offset).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<CoinsResponse> call, Response<CoinsResponse> response) {
                if(response.body() != null && response.isSuccessful()){
                    data.setValue(new Resource.Success<>(response.body()));
                }else{
                    data.setValue(new Resource.Error<>("Some goes wrong"));
                }
            }

            @Override
            public void onFailure(Call<CoinsResponse> call, Throwable t) {
                data.setValue(new Resource.Error<>(t.getLocalizedMessage()));
            }
        });
        return data;
    }

}
