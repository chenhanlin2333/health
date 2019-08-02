package com.chen.app_ec.foodvs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.search.SearchActivity;
import com.chen.app_ec.search.TextViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class VsFoodactivity extends BaseActivity {

    public static final int img_left = 0;
    public static final int img_right =1;
    public static final String VSFOODDATA = "vsfood";


    @BindView(R2.id.tl_back)
    Toolbar mToolbar;

    @BindView(R2.id.rv_left)
    RecyclerView rv_left;

    @BindView(R2.id.rv_middle)
    RecyclerView rv_middle;

    @BindView(R2.id.rv_right)
    RecyclerView rv_right;


    @OnClick(R2.id.img_left)
    void onClickleft(){
        SearchActivity.startWithVs(this,img_left);
    }

    @OnClick(R2.id.img_right)
    void onClickRight(){
        SearchActivity.startWithVs(this,img_right);
    }

    TextViewAdapter mAdapter_left;
    TextViewAdapter mAdapter_middle;
    TextViewAdapter mAdapter_right;

    ArrayList<String> data_left = new ArrayList<>();
    ArrayList<String> data_middle = new ArrayList<>();
    ArrayList<String> data_right = new ArrayList<>();


    @Override
    public int getLayoutid() {
        return R.layout.activity_vs_foodactivity;
    }

    @Override
    public void havebind() {
        inittoolbar();
        initrecycleview();
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

    public static void start(Context mContext){
        Intent intent = new Intent(mContext, VsFoodactivity.class);
        mContext.startActivity(intent);
    }

    private void initrecycleview(){
        data_middle.add("热量");
        data_middle.add("蛋白质");
        data_middle.add("脂肪");
        data_middle.add("碳水化合物");
        data_middle.add("膳食纤维");
        data_middle.add("GI");
        data_middle.add("GL");
        data_middle.add("维生素A");
        data_middle.add("维生素C");
        data_middle.add("维生素E");
        data_middle.add("胡萝卜素");
        data_middle.add("维生素B1");
        data_middle.add("维生素B2");
        data_middle.add("烟酸");
        data_middle.add("胆固醇");
        data_middle.add("镁");
        data_middle.add("钙");
        data_middle.add("铁");
        data_middle.add("锌");
        data_middle.add("铜");
        data_middle.add("锰");
        data_middle.add("钾");
        data_middle.add("磷");
        data_middle.add("纳");
        data_middle.add("硒");


        mAdapter_left = new TextViewAdapter(this,data_left);
        mAdapter_middle = new TextViewAdapter(this,data_middle);
        mAdapter_right = new TextViewAdapter(this,data_right);
        rv_left.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_middle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_right.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_left.setAdapter(mAdapter_left);
        rv_middle.setAdapter(mAdapter_middle);
        rv_right.setAdapter(mAdapter_right);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == img_left){
            if (resultCode == RESULT_OK){
                FoodData foodData = (FoodData) data.getSerializableExtra(VSFOODDATA);
            }
        }else if (requestCode == img_right){
            if (resultCode == RESULT_OK){
                FoodData foodData = (FoodData) data.getSerializableExtra(VSFOODDATA);
            }
        }
    }
}
