package com.mycompany.testappjava.ui.viewmodels;

import android.os.Build;
import android.os.CountDownTimer;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mycompany.testappjava.data.Coin;
import com.mycompany.testappjava.data.CoinListItemModel;
import com.mycompany.testappjava.data.Resource;
import com.mycompany.testappjava.data.state.MainScreenState;
import com.mycompany.testappjava.repositories.CoinrankingRepository;

import java.util.ArrayList;
import java.util.List;


public class MainViewModel extends ViewModel {

    final int BY_PRICE = 0;
    final int BY_MARKER_CAP = 1;
    final int BY_24H = 2;

    final int PAGINATION_LIMIT = 50;

    private final MutableLiveData<List<CoinListItemModel>> _coinsData = new MutableLiveData<>();
    public LiveData<List<CoinListItemModel>> coinsData = _coinsData;

    private final MutableLiveData<MainScreenState> _mainScreenState = new MutableLiveData<>();
    public LiveData<MainScreenState> mainScreenState = _mainScreenState;

    private final ArrayList<Coin> coins = new ArrayList<>();

    private boolean isAsc = true;
    private int currentListOrder = BY_MARKER_CAP;

    private int paginationPosition = 0;
    private int paginationTotal = 10;


    public MainViewModel(){
        _mainScreenState.postValue(new MainScreenState(true));
        updateData();
    }

    //Get coins data from repository
    private void updateData(){
        String sortDirection = "asc";
        if(!isAsc){sortDirection = "desc";}
        String orderedBy = "price";
        if(currentListOrder == BY_MARKER_CAP){
            orderedBy = "marketCap";
        }else if(currentListOrder == BY_24H){
            orderedBy = "24hVolume";
        }

        CoinrankingRepository.getInstance().getCoins(orderedBy, sortDirection, PAGINATION_LIMIT, paginationPosition).observeForever(coinsResponseResource -> {
            if(coinsResponseResource instanceof Resource.Success){
                coins.addAll(coinsResponseResource.data.data.coins);
                paginationTotal = coinsResponseResource.data.data.stats.total;
                prepareData();
                paginationPosition += PAGINATION_LIMIT;
            }else if(coinsResponseResource instanceof Resource.Loading){
                _mainScreenState.postValue(new MainScreenState(true));
            }else if(coinsResponseResource instanceof Resource.Error){
                _mainScreenState.postValue(new MainScreenState(true, coinsResponseResource.error));
            }
        });
    }


    //Prepare data with sort and price
    private void prepareData(){
        ArrayList<CoinListItemModel> list = new ArrayList<>();
        for (Coin coin : coins){
            float value = 0F;
            switch (currentListOrder){
                case BY_MARKER_CAP:{
                    if(coin.marketCap != null){
                        value = Float.parseFloat(coin.marketCap);
                    }
                    break;
                }
                case BY_24H:{
                    if(coin.volume24 != null){
                        value = Float.parseFloat(coin.volume24);
                    }
                    break;
                }
                case BY_PRICE:{
                    if(coin.price != null){
                        value = Float.parseFloat(coin.price);
                    }
                    break;
                }
            }
            list.add(new CoinListItemModel(coin.symbol, coin.name, coin.iconUrl, value, coin.change, coin.sparkline));
        }
        _coinsData.postValue(list);

        _mainScreenState.postValue(new MainScreenState(currentListOrder, isAsc));
    }

    //Change coins values by 24H or Market Cap or Price
    public void onChangeVisibleValues(){
        currentListOrder++;
        if(currentListOrder > BY_24H){
            currentListOrder = BY_PRICE;
            isAsc = !isAsc;
        }
        paginationPosition = 0;
        coins.clear();
        updateData();
    }

    //Refresh data
    public void onRefresh() {
        paginationPosition = 0;
        coins.clear();
        updateData();
    }

    //Pagination
    public void onLastItemShowed(){
        updateData();
    }
}
