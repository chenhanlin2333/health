package com.chen.app_ec.Ec_core_content;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chen.app_core.app.BaseFragment;
import com.chen.app_ec.Ec_core_content.adapter.DayOffoodAdapter;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.data.DayFood;
import com.chen.app_ec.data.DayFoodDiary;
import com.chen.app_ec.data.FoodDiary;
import com.chen.app_ec.foodvs.VsFoodactivity;
import com.chen.app_ec.newmyfood.NewFoodActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFoodFragment extends BaseFragment {

    @BindView(R2.id.Rv_dayoffood)
    RecyclerView rv_foodOfDay;
    DayOffoodAdapter mAdapter;

    @BindView(R2.id.tx_sumFood)
    TextView tv_sumFood;

    @BindView(R2.id.tv_sum_calorie)
    TextView tv_caloire;

    @BindView(R2.id.tv_sum_danbai)
    TextView tv_danbai;

    @BindView(R2.id.tv_sum_zhifang)
    TextView tv_zhifang;

    @BindView(R2.id.tv_sum_tanshui)
    TextView tv_tanshui;

    @BindView(R2.id.tv_sum_weishenshu)
    TextView tv_weishenshu;
    @BindView(R2.id.tv_startvs)
    TextView tv_vs;

    private ArrayList<DayFoodDiary> mDayFoodDiaryArrayList ;



    @OnClick(R2.id.tv_startvs)
    void openvsActivity(){
        VsFoodactivity.start(getActivity());
    }

    @Override
    public Object setlayout() {
        return R.layout.fragment_myfood;
    }


    @OnClick(R2.id.tv_gotonewfood)
    void startnewFoodactivity(){
        NewFoodActivity.start(getActivity());
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {
        initRecycleView();
    }

    private void initRecycleView(){
        mAdapter = new DayOffoodAdapter(getActivity(),mDayFoodDiaryArrayList);
        rv_foodOfDay.setAdapter(mAdapter);
        rv_foodOfDay.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        tv_sumFood.setFocusable(true);
    }

    //TODO 网络获取数据
    private void getData(){

    }


}
