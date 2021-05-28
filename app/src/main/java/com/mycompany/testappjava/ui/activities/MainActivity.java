package com.mycompany.testappjava.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.mycompany.testappjava.R;
import com.mycompany.testappjava.databinding.ActivityMainBinding;
import com.mycompany.testappjava.ui.adapters.ValuesListAdapter;
import com.mycompany.testappjava.ui.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    private ValuesListAdapter valuesListAdapter;

    //Prepare view model, view binding, observe on view model, set on click listener to change showed values type
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        valuesListAdapter = new ValuesListAdapter();
        binding.rvValues.setAdapter(valuesListAdapter);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModelObserve();
        binding.tvSelectedValues.setOnClickListener(v -> viewModel.onChangeVisibleValues());
        binding.swipeRefresh.setOnRefreshListener(() -> {
            viewModel.onRefresh();
            binding.swipeRefresh.setRefreshing(false);
        });

        valuesListAdapter.setValuesCallback(() -> {
            viewModel.onLastItemShowed();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoading();
    }

    private void viewModelObserve(){
        viewModel.mainScreenState.observe(this, mainScreenState -> {
            if(mainScreenState.isError){
                Toast.makeText(this, mainScreenState.errorDescription, Toast.LENGTH_SHORT).show();
                hideLoading();

            }else {
               hideLoading();
                if(mainScreenState.currentValues == 0){
                    binding.tvSelectedValues.setText(getString(R.string.price));
                }
                else if(mainScreenState.currentValues == 1){
                    binding.tvSelectedValues.setText(getString(R.string.marker_cap));
                }
                else{
                    binding.tvSelectedValues.setText(getString(R.string.value_24H));
                }
                if(mainScreenState.isSortAsk){
                    binding.ivSort.setImageResource(android.R.drawable.arrow_up_float);
                }else{
                    binding.ivSort.setImageResource(android.R.drawable.arrow_down_float);
                }

            }
        });

        viewModel.coinsData.observe(this, itemsList ->{
            valuesListAdapter.submitList(itemsList);
            valuesListAdapter.notifyDataSetChanged();
            binding.rvValues.setVisibility(View.VISIBLE);
        });
    }

    //Hide recycler view and show shimmer loading
    private void showLoading(){
        binding.shimmerViewContainer.setVisibility(View.VISIBLE);
        binding.shimmerViewContainer.startShimmerAnimation();
        binding.rvValues.setVisibility(View.GONE);
    }

    //Hide shimmer loading
    private void hideLoading(){
        binding.shimmerViewContainer.stopShimmerAnimation();
        binding.shimmerViewContainer.setVisibility(View.GONE);
    }
}