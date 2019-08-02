package com.chen.app_ec.detailfood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.Ec_core_content.adapter.NutritionAdapter;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.data.Nutrition;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

public class DetailNutritionActivity extends BaseActivity {


    @BindView(R2.id.rc_nutrition)
    RecyclerView rv_nutrition;

    @BindView(R2.id.tl_back)
    Toolbar mToolbar;

    NutritionAdapter mNutritionAdapter ;



    @Override
    public int getLayoutid() {
        return R.layout.activity_detail_nutrition;
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


    public static void start(Context mContext, FoodData foodData){
        Intent intent = new Intent(mContext, DetailNutritionActivity.class);
        mContext.startActivity(intent);
    }

}
