package com.chen.app_ec.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.data.FoodData;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.detailfood.DetailFoodActivity;
import com.chen.app_ec.foodvs.VsFoodactivity;

import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements ViewHolderOnclick, ViewHolderDataOnclick<FoodData>, ViewholderDataOnclick1<FoodData> {

    @BindView(R2.id.search_toolbar)
    Toolbar searchtoolbar;

    @BindView(R2.id.sv_food)
    SearchView sv_food;

    @BindView(R2.id.tv_selcet_show)
    TextView tv_show_selcet;

    @BindView(R2.id.img_up_down)
    ImageView img_down;


    @BindView(R2.id.selcet_recommend)
    CheckBox mCheckBox;

    @BindView(R2.id.rc_searchfood)
    RecyclerView rc_showsearch;

    SearchView.SearchAutoComplete mSearchAutoComplete;
    SearchFoodAdaper mSearchFoodAdaper;

    TextViewAdapter mTextViewAdapter ;
    PopupWindow popupWindow;

    static boolean isSearch =true;


    @Override
    public int getLayoutid() {
        return R.layout.activity_search;
    }


    @OnClick(R2.id.img_up_down)
    public void showPopwindow(){
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
        RecyclerView  rv_select = contentView.findViewById(R.id.rv_select_nutrition);
        rv_select.setLayoutManager(new GridLayoutManager(this,3));
        if (mTextViewAdapter == null){
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("常见");
            arrayList.add("热量");
            arrayList.add("蛋白质");
            arrayList.add("脂肪");
            arrayList.add("膳食纤维");
            arrayList.add("碳水化合物");
            arrayList.add("维生素A");
            mTextViewAdapter = new TextViewAdapter(this,arrayList);
            mTextViewAdapter.setViewHolderOnclick(this);
        }
        img_down.setSelected(true);
        rv_select.setAdapter(mTextViewAdapter);
        popupWindow = new PopupWindow(contentView,
                LayoutParams.MATCH_PARENT, 400, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        darkenBackground(0.6f);
        popupWindow.showAsDropDown(img_down);

    }

    @Override
    public void havebind() {

        inittoolbar();
        initSearchView();
        initRecycleview();
    }

    private void initSearchView(){
        mSearchAutoComplete = sv_food.findViewById(R.id.search_src_text);
        sv_food.setIconified(false);
        sv_food.setQueryHint("你可以搜索你喜欢的食物");
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


    private void inittoolbar(){
        setSupportActionBar(searchtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void initRecycleview(){
        mSearchFoodAdaper = new SearchFoodAdaper(this,new ArrayList(),isSearch);
        mSearchFoodAdaper.setViewHolderOnclick(this);
        mSearchFoodAdaper.setFoodDataViewholderDataOnclick1(this);
        rc_showsearch.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rc_showsearch.setAdapter(mSearchFoodAdaper);
    }

    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return false;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        TextView textView = view.findViewById(R.id.tv_text);
        String nurtition = textView.getText().toString();
        tv_show_selcet.setText(nurtition);
        img_down.setSelected(false);
        darkenBackground(1.0f);
        popupWindow.dismiss();
    }

    @Override
    public void onItemClick1(FoodData foodData) {
        DetailFoodActivity.start(this,null);
    }

    @Override
    public void onItemClick(FoodData foodData) {
        Intent intent = new Intent();
        intent.putExtra(VsFoodactivity.VSFOODDATA,foodData);
        Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    public void onLongClick(View view) {

    }

    public static void startWithVs( Activity activity,int requestCode){
        isSearch = false;
        activity.startActivityForResult(new Intent(activity,SearchActivity.class),requestCode);
    }
    public static void startWithSearch( Context context){
        isSearch = true;
        context.startActivity(new Intent(context,SearchActivity.class));
    }


}
