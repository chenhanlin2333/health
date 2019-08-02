package com.chen.app_ec.detailfood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.Ec_core_content.adapter.NutritionAdapter;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.data.Nutrition;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailFoodActivity extends BaseActivity {


    @BindView(R2.id.rc_nutrition)
    RecyclerView rv_nutrition;

    @BindView(R2.id.tl_back)
    Toolbar mToolbar;
    NutritionAdapter mNutritionAdapter;
    //TODO  数据
    @OnClick(R2.id.tv_more_nutrition)
    void start(){
        DetailNutritionActivity.start(this,null);
    }

    @BindView(R2.id.tv_qianka)
    TextView tv_qianka;

    @BindView(R2.id.tv_qianjiao)
    TextView tv_qianjiao;

    @OnClick(R2.id.tv_qianka)
    void clickka(){
        tv_qianka.setBackgroundResource(R.drawable.shape_select);
        tv_qianjiao.setBackground(null);
    }

    @OnClick(R2.id.tv_qianjiao)
    void clickjiao(){
        tv_qianjiao.setBackgroundResource(R.drawable.shape_select);
        tv_qianka.setBackground(null);
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_detail_food;
    }

    @Override
    public void havebind() {

        inittoolbar();
        mNutritionAdapter = new NutritionAdapter(this,new ArrayList<Nutrition>() );
        rv_nutrition.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_nutrition.setAdapter(mNutritionAdapter);

    }

    private void inittoolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public static void start( Context mContext,FoodData foodData){
        Intent intent = new Intent(mContext, DetailFoodActivity.class);
        mContext.startActivity(intent);
    }


}
