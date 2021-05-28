package com.mycompany.testappjava.api;

import com.mycompany.testappjava.data.CoinsResponse;
import com.mycompany.testappjava.data.CryptoListModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CoinrankingApi {


    @GET("coins")
    Call<CoinsResponse> getCoins(@Header("x-access-token") String token,
                                 @Query("orderBy") String orderBy,
                                 @Query("orderDirection") String orderDirection,
                                 @Query("limit") Integer limit,
                                 @Query("offset") Integer offset);

}
