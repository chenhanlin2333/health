package com.chen.app_ec.Ec_core_content;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.chen.app_core.app.BaseFragment;
import com.chen.app_ec.Ec_core_content.adapter.HotFoodAdapter;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.Ec_core_content.adapter.Foodadapter;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.foodvs.VsFoodactivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchFoodFragment extends BaseFragment {
    SearchView.SearchAutoComplete mSearchAutoComplete;

    @BindView(R2.id.search_toolbar)
    Toolbar mSearchToolbar;

    @BindView(R2.id.sv_food)
    SearchView sv_food;

    @BindView(R2.id.rv_showfood_morning)
    RecyclerView mRecyclerView_morning;

    @BindView(R2.id.rv_showfood_noon)
    RecyclerView mRecyclerView_noon;

    @BindView(R2.id.rv_showfood_dinner)
    RecyclerView mRecyclerView_dinner;

    @BindView(R2.id.re_hotfood)
    RecyclerView mRecyclerView_hotfood;





    @Override
    public Object setlayout() {
        return R.layout.fragment_searchfood;
    }



    Foodadapter food_moring;
    Foodadapter food_noon;
    Foodadapter food_dinner;
    HotFoodAdapter food_hot;

    @Override
    public void bindview(Bundle savedInstance, View rootview){
        //设置Searchview
        searchViewSet();
        //初始化recyclerview 早中晚餐
        initRecyclerview();
    }

    private void initRecyclerview(){
        ArrayList<FoodData> dataMorning = new ArrayList<>();
        food_moring = new Foodadapter(getActivity(), dataMorning);
        mRecyclerView_morning.setAdapter(food_moring);
        mRecyclerView_morning.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FoodData> dataNoon = new ArrayList<>();
        food_noon = new Foodadapter(getActivity(), dataNoon);
        mRecyclerView_noon.setAdapter(food_moring);
        mRecyclerView_noon.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FoodData> dataDinner = new ArrayList<>();
        food_dinner = new Foodadapter(getActivity(), dataDinner);
        mRecyclerView_dinner.setAdapter(food_moring);
        mRecyclerView_dinner.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.HORIZONTAL,false));

        //TODO 只有第三个Recycleview可以点击
        ArrayList<FoodData> hot_food = new ArrayList<>();
        food_hot = new HotFoodAdapter(getActivity(),hot_food);
        mRecyclerView_hotfood.setAdapter(food_hot);
        mRecyclerView_hotfood.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }


    private void searchViewSet(){
        mSearchAutoComplete = sv_food.findViewById(R.id.search_src_text);
        sv_food.setIconified(false);
        sv_food.setQueryHint("你可以搜索你喜欢的食物");
        sv_food.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
        //TODO 搜索进行联网操作直接跳
        sv_food.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }



}
