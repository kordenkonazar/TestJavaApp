package com.mycompany.testappjava.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.mycompany.testappjava.R;
import com.mycompany.testappjava.data.CoinListItemModel;
import com.mycompany.testappjava.databinding.ItemCryptoBinding;
import com.mycompany.testappjava.utils.GlideApp;
import com.mycompany.testappjava.utils.ImageGlideModule;

import java.math.BigDecimal;
import java.util.ArrayList;

//Recycler view adapter based on ListAdapter with Diffutils
public class ValuesListAdapter extends ListAdapter<CoinListItemModel, ValuesListAdapter.ValuesVH> {

    private ValuesCallback valuesCallback = null;

    public ValuesListAdapter() {
        super(new ValuesDiff());
    }

    //Create view holder with ViewBinding
    @NonNull
    @Override
    public ValuesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ValuesVH(ItemCryptoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    //Set values into view item
    @Override
    public void onBindViewHolder(@NonNull ValuesVH holder, int position) {
        holder.bind(getItem(position));
        if(getCurrentList().size()-1 == position && valuesCallback != null){
            valuesCallback.onLastItemShowed();
        }
    }

    public void setValuesCallback(ValuesCallback valuesCallback){
        this.valuesCallback = valuesCallback;
    }

    public interface ValuesCallback{
        void onLastItemShowed();
    }

    //View holder to prepare and set values into view
    public class ValuesVH extends RecyclerView.ViewHolder{
        private ItemCryptoBinding binding;

        public ValuesVH(@NonNull ItemCryptoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        //Prepare and set values from model
        public void bind(CoinListItemModel model){
            GlideApp.with(itemView).load(model.iconUrl).centerCrop().into(binding.ivCurrency);
            ArrayList<Integer> sparkItems = new ArrayList<>();
            int firstItemValue = 0;
            boolean isAllItemsSame = true;
            for(int i = 0; i < model.sparkline.size(); i++){
                if(model.sparkline.get(i) != null){
                    int itemValue = Math.round(Float.parseFloat(model.sparkline.get(i)));
                    if(i == 0){
                        firstItemValue = itemValue;
                    }else if(firstItemValue != itemValue){
                        isAllItemsSame = false;
                    }
                    sparkItems.add(itemValue);
                }
            }
            if(isAllItemsSame){
                sparkItems.add(0, firstItemValue - 1);
            }
            binding.sparkview.setData(sparkItems);
            binding.tvCurrencyName.setText(model.name);
            binding.tvCurrencyShort.setText(model.symbol);
            if(model.value != null) {
                binding.tvPrice.setText("$ " + model.getPriceFormat());
            }

            if(model.change != null){
                binding.tvPercent.setText(String.format("%.2f", Float.parseFloat(model.change)) + "%");
            }
        }
    }


    //DiffUtils to compare items
    public static class ValuesDiff extends DiffUtil.ItemCallback<CoinListItemModel>{

        @Override
        public boolean areItemsTheSame(@NonNull CoinListItemModel oldItem, @NonNull CoinListItemModel newItem) {
            return oldItem.getName() == newItem.getName() && oldItem.getSymbol() == newItem.getSymbol();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CoinListItemModel oldItem, @NonNull CoinListItemModel newItem) {
            return false;
        }
    }
}
