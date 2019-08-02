package com.chen.app_ec.newmyfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.data.DayFoodDiary;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.data.FoodDiary;
import com.chen.app_ec.search.SearchActivity;
import com.chen.app_ec.search.SearchFoodAdaper;
import com.chen.app_ec.search.ViewholderDataOnclick1;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class NewFoodSearchActivity extends BaseActivity implements  ViewholderDataOnclick1<FoodData>, DialogFragmentdismisslistener<FoodDiary> {

    @BindView(R2.id.sv_food)
    SearchView sv_food;

    @BindView(R2.id.rc_searchfood)
    RecyclerView rc_showsearch;

    @BindView(R2.id.tv_num_of_complete)
    TextView tv_num;

    @BindView(R2.id.tl_back)
    Toolbar tl_back;

    @OnClick(R2.id.tv_complete)
    void goback(){
        //TODO 设置食物并返回
        Intent intent = new Intent();
        intent.putExtra(NewFoodActivity.FOODDATA,mFoodDiaries);
        setResult(RESULT_OK,intent);
        finish();
    }

    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private SearchFoodAdaper mSearchFoodAdaper;
    DialogFoodfragment mFoodDialog;
    private ArrayList<FoodDiary> mFoodDiaries = new ArrayList<>();

    @Override
    public int getLayoutid() {
        return R.layout.activity_new_food_search;
    }

    @Override
    public void havebind() {
        initSearchView();
        initRecycleview();
        initDialogfragment();
        inittoolbar();
    }

    private void inittoolbar(){
        setSupportActionBar(tl_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tl_back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initSearchView(){
        mSearchAutoComplete = sv_food.findViewById(R.id.search_src_text);
        sv_food.setIconified(false);
        sv_food.setQueryHint("搜索");
        //TODO 搜索
        sv_food.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
        sv_food.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void initDialogfragment(){
        mFoodDialog = new DialogFoodfragment();
        mFoodDialog.setFoodDiaryDialogFragmentdismisslistener(this);
    }


    //TODO 联网获取arraylist
    private void initRecycleview(){
        mSearchFoodAdaper = new SearchFoodAdaper(this,new ArrayList(),true);
        mSearchFoodAdaper.setFoodDataViewholderDataOnclick1(this);
        rc_showsearch.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rc_showsearch.setAdapter(mSearchFoodAdaper);
    }


    @Override
    public void onItemClick1(FoodData foodData) {
        mFoodDialog.setFoodData(foodData);
        mFoodDialog.show(getSupportFragmentManager(),"dialog");
    }

    //入口
    public static void start(Activity activity, int requestCode){
        activity.startActivityForResult(new Intent(activity, NewFoodSearchActivity.class),requestCode);
    }

    private void setTvnumShow(){
        if (mFoodDiaries.size()>0){
            tv_num.setText("1");
            tv_num.setVisibility(View.VISIBLE);
        }
        if (mFoodDiaries.size() ==0){
            tv_num.setVisibility(View.GONE);
        }
    }


    @Override
    public void setDialogResult(FoodDiary foodDiary) {
        mFoodDiaries.add(foodDiary);
        setTvnumShow();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
